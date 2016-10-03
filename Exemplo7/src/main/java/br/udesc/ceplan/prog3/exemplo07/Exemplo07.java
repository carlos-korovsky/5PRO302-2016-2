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
package br.udesc.ceplan.prog3.exemplo07;

import br.udesc.ceplan.prog3.exemplo07.config.Configuration;
import br.udesc.ceplan.prog3.exemplo07.dao.ClienteDao;
import br.udesc.ceplan.prog3.exemplo07.dao.DaoFactory;
import br.udesc.ceplan.prog3.exemplo07.dao.PedidoDao;
import br.udesc.ceplan.prog3.exemplo07.modelo.Cliente;
import br.udesc.ceplan.prog3.exemplo07.modelo.Pedido;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class Exemplo07 {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        Properties properties = new Properties();
        properties.setProperty("database", "hsqldb");
        
        Configuration.getInstance().loadConfiguration(properties);
        
        DaoFactory dao = DaoFactory.getFactory();
        ClienteDao clienteDao = dao.getClienteDao();
        PedidoDao pedidoDao = dao.getPedidoDao();
        Cliente cliente;
        Pedido pedido;
        
        dao.startTransaction();
        cliente = new Cliente();
        cliente.setNome("Teste 00");
        clienteDao.create(cliente);
        dao.commitTransaction();
        
        dao.startTransaction();
        cliente = new Cliente();
        cliente.setNome("Teste 01");
        clienteDao.create(cliente);
        
        pedido = new Pedido();
        pedido.setCliente(cliente);
        pedidoDao.create(pedido);
        dao.rollbackTransaction();
        
        dao.startTransaction();
        cliente = new Cliente();
        cliente.setNome("Teste 02");
        cliente = clienteDao.create(cliente);
        
        pedido = new Pedido();
        pedido.setCliente(cliente);
        pedidoDao.create(pedido);
        
        pedido = new Pedido();
        pedido.setCliente(cliente);
        pedidoDao.create(pedido);
        
        pedido = new Pedido();
        pedido.setCliente(cliente);
        pedidoDao.create(pedido);
        
        dao.commitTransaction();
        
        for (Cliente aux: clienteDao.retrieveAll()) {
            aux.setPedidos(pedidoDao.findByCliente(aux));
            System.out.println(aux);
        }
    }
    
}
