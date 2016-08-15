/*
 * Copyright (C) 2016 UDESC - CEPLAN
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.udesc.ceplan.prog3.exemplo01.servidor;

import br.udesc.ceplan.prog3.exemplo01.servidor.interfaces.NovoClienteOuvinte;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ServerSocketFactory;
import org.slf4j.LoggerFactory;
import br.udesc.ceplan.prog3.exemplo01.servidor.interfaces.ConexaoOuvinte;
import br.udesc.ceplan.prog3.exemplo01.servidor.threads.MonitorConexao;
import br.udesc.ceplan.prog3.exemplo01.servidor.threads.MonitorNovaConexao;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.slf4j.Logger;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@udesc.br>
 */
public class Servidor implements NovoClienteOuvinte, ConexaoOuvinte {

    private static Servidor aplicativo;
    
    private ServerSocket ssocker;
    private final Logger logger;
    private final Map<Socket, Thread> clientes;
    private Thread monitorNovaConexao;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Servidor.aplicativo = new Servidor();
        byte[] addr = {0,0,0,0};
        InetAddress endereco = InetAddress.getByAddress(addr);
        Integer porta = 3377;
        Servidor.aplicativo.start(endereco, porta);
        
        Servidor.aplicativo.waitUntilFinished();
    }
    
    public Servidor() {
        this.logger = LoggerFactory.getLogger(getClass());
        this.clientes = new HashMap<>();
    }
    
    private void init(InetAddress endereco, Integer porta) throws IOException {
        ServerSocketFactory factory = ServerSocketFactory.getDefault();
        this.ssocker = factory.createServerSocket(porta, 5, endereco);
        MonitorNovaConexao monitorNovaConexaoRunnable;
        monitorNovaConexaoRunnable = new MonitorNovaConexao(this.ssocker);
        monitorNovaConexaoRunnable.addOuvinte(this);
        this.monitorNovaConexao = new Thread(monitorNovaConexaoRunnable);
    }

    public void start(InetAddress endereco, Integer porta) throws IOException {
        this.init(endereco, porta);
        this.monitorNovaConexao.start();
    }
    
    public void waitUntilFinished() throws InterruptedException {
        this.monitorNovaConexao.join();
    }
    
    @Override
    public void onNovoCliente(Socket novoCliente) {
        if (!this.clientes.containsKey(novoCliente)) {
            MonitorConexao monitorConexaoRunnable;
            monitorConexaoRunnable = new MonitorConexao(novoCliente);
            monitorConexaoRunnable.addOuvinte(this);
            Thread threadMonitorConexao = new Thread(monitorConexaoRunnable);
            this.clientes.put(novoCliente, threadMonitorConexao);
            threadMonitorConexao.start();
            try {
                PrintWriter socketOut = new PrintWriter(novoCliente.getOutputStream(), true);
                socketOut.println("Bem vindo ao servidor!");
            } catch (IOException ex) {
                logger.error(ex.getLocalizedMessage());
            }
        }
    }

    @Override
    public void onNovaMensagem(Socket origem, String mensagem) {
        logger.debug("Mensagem recebida de {}:{} ({})", 
                            origem.getInetAddress(), origem.getPort(), mensagem);
        for (Map.Entry<Socket, Thread> entry : clientes.entrySet()) {
            Socket destino = entry.getKey();
            if (!destino.equals(origem)) {
                try {
                    PrintWriter out;
                    out = new PrintWriter(destino.getOutputStream(), true);
                    out.println(mensagem);
                    logger.debug("Mensagem enviada de {}:{} para {}:{}", 
                            origem.getInetAddress(), origem.getPort(), 
                            destino.getInetAddress(), destino.getPort());
                } catch (IOException ex) {
                    logger.error(ex.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    public void onClienteDesconectado(Socket cliente) {
        logger.debug("Cliente {}:{} desconectado, removendo da lista", 
                cliente.getInetAddress(), cliente.getPort());
        if (this.clientes.containsKey(cliente)) {
            Thread thread = this.clientes.get(cliente);
            try {
                thread.join(1000);
            } catch (InterruptedException ex) {
                logger.error(ex.getLocalizedMessage());
            } finally {
                this.clientes.remove(cliente);
                logger.debug("Cliente removido com sucesso!");                
            }
        }
    }
    
}
