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
package br.udesc.ceplan.prog3.exemplo01.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@udesc.br>
 */
public class Cliente {

    private static Cliente application;
    
    private final BufferedReader stdIn;
    private final Logger logger;
    
    private Socket socket;
    private PrintWriter socketOut;
    private Thread monitorConexao;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        Cliente.application = new Cliente();
        InetAddress address = InetAddress.getByName(args[0]);
        Integer port = Integer.parseInt(args[1]);
        Cliente.application.start(address, port);
        Cliente.application.waitUntilFinished();
    }
    
    public Cliente() {
        this.logger = LoggerFactory.getLogger(getClass());
        this.stdIn = new BufferedReader(new InputStreamReader(System.in));
    }
    
    private void init(InetAddress address, Integer port) throws IOException {
        this.socket = new Socket(address, port);
        socketOut = new PrintWriter(socket.getOutputStream(), true);
        MonitorConexao monitorConexaoRunnable = new MonitorConexao(this.socket);
        this.monitorConexao = new Thread(monitorConexaoRunnable);
    }
    
    public void start(InetAddress address, Integer port) throws IOException {
        this.init(address, port);
        this.monitorConexao.start();
        logger.debug("Socket conectado no servidor...");
        String userInput = "";        
        while (userInput != null) {
            userInput = stdIn.readLine();
            if (userInput.equalsIgnoreCase("")) {
                userInput = null;
            } else {
                logger.debug("Enviando mensagem: {}", userInput);
                socketOut.println(userInput);
            }
        }
    }
    
    public void waitUntilFinished() throws InterruptedException, IOException {
        logger.debug("Desconectado socket do servidor...");        
        this.socket.close();
        this.monitorConexao.join(1000);
        
    }
    
}
