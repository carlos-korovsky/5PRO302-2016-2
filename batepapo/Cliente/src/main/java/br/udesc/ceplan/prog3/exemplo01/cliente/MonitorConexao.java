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
import java.net.Socket;
import javafx.collections.ObservableList;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class MonitorConexao implements Runnable {

    private final org.slf4j.Logger logger;
    private final Socket conexao;
    private BufferedReader socketIn;
    private OnNewMessageListener<String> mensagens;
    
    public MonitorConexao(Socket conexao) {
        this(conexao, null);
    }
    
    public MonitorConexao(Socket conexao, OnNewMessageListener<String> mensagens) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.conexao = conexao;
        this.mensagens = mensagens;
    }
    
    @Override
    public void run() {
        try {
            socketIn = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream()));
            String message;
            while (this.conexao.isConnected() && !this.conexao.isClosed()) {
                message = socketIn.readLine();
                logger.info("Mensagem recebida: {}", message);
                this.addMensagem(message);

            }
        } catch (IOException ex) {
            logger.error(ex.getLocalizedMessage());
        }
    }
    
    private synchronized void addMensagem(String mensagem) {
        if (this.mensagens != null) {
            this.mensagens.OnNewMessage(mensagem);
        }
    }
}
