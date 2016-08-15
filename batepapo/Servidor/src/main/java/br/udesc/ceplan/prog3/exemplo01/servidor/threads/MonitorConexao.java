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
package br.udesc.ceplan.prog3.exemplo01.servidor.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.udesc.ceplan.prog3.exemplo01.servidor.interfaces.ConexaoOuvinte;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class MonitorConexao implements Runnable {
    
    private final Logger logger;
    private final Socket cliente;
    private final List<ConexaoOuvinte> ouvintes;
    
    public MonitorConexao(Socket cliente) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.cliente = cliente;
        this.ouvintes = new LinkedList<>();
    }

    @Override
    public void run() {
        BufferedReader in = null;
        try {
            InputStreamReader inputStreamReader;
            inputStreamReader = new InputStreamReader(this.cliente.getInputStream());
            in = new BufferedReader(inputStreamReader);
            String mensagem = "";
            while (mensagem != null) {
                mensagem = in.readLine();
                if ((mensagem == null) || (mensagem.equalsIgnoreCase(""))) {
                    mensagem = null;
                } else {
                    this.onNovaMensagem(mensagem);
                }
            }
        } catch (IOException ex) {
            logger.error(ex.getLocalizedMessage());
        } finally {
            if (this.cliente != null)
                this.onClienteDesconectado(cliente);
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    logger.error(ex.getLocalizedMessage());
                }
            }
        }   
    }
    
    private synchronized void onClienteDesconectado(Socket cliente) {
        logger.debug("Cliente {}:{} desconectado, avisando demais classes", 
                cliente.getInetAddress(), cliente.getPort());
        for (ConexaoOuvinte ouvinte: this.ouvintes) {
            ouvinte.onClienteDesconectado(cliente);
        }
    }
    
    private synchronized void onNovaMensagem(String mensagem) {
        logger.debug("Nova mensagem recebida de {}:{} avisando demais clientes", 
                this.cliente.getInetAddress(), this.cliente.getPort());
        for (ConexaoOuvinte ouvinte: this.ouvintes) {
            ouvinte.onNovaMensagem(this.cliente, mensagem);
        }
    }
    
    public void addOuvinte(ConexaoOuvinte ouvinte) {
        logger.debug("Adicionado um novo ouvinte a lista de ouvintes...");
        this.ouvintes.add(ouvinte);
    }
    
    public void removerOuvinte(ConexaoOuvinte ouvinte) {
        logger.debug("Removido um ouvinte da lista de ouvintes...");
        this.ouvintes.remove(ouvinte);
    }
}
