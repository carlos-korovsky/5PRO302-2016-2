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

import br.udesc.ceplan.prog3.exemplo01.servidor.interfaces.NovoClienteOuvinte;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@udesc.br>
 */
public class MonitorNovaConexao implements Runnable {
    
    private final Logger logger;
    private final ServerSocket servidor;
    private final List<NovoClienteOuvinte> ouvintes;
    
    public MonitorNovaConexao(ServerSocket servidor) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.servidor = servidor;
        this.ouvintes = new LinkedList<>();
    }

    @Override
    public void run() {
        Socket novoCliente;
        while (this.servidor.isBound() && !this.servidor.isClosed()) {
            try {
                novoCliente = this.servidor.accept();
                this.onNovoCliente(novoCliente);
            } catch (IOException ex) {
                logger.error(ex.getLocalizedMessage());
            }
        }
    }
    
    private synchronized void onNovoCliente(Socket novoCliente) {
        logger.debug("Novo cliente conectado, avisando classes ouvintes...");
        for (NovoClienteOuvinte ouvinte: this.ouvintes) {
            ouvinte.onNovoCliente(novoCliente);
        }
    }
    
    public void addOuvinte(NovoClienteOuvinte ouvinte) {
        logger.debug("Adicionado um novo ouvinte a lista de ouvintes...");
        this.ouvintes.add(ouvinte);
    }
    
    public void removerOuvinte(NovoClienteOuvinte ouvinte) {
        logger.debug("Removido um ouvinte da lista de ouvintes...");
        this.ouvintes.remove(ouvinte);
    }
    
}
