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
package br.udesc.ceplan.prog3.exemplo07.dao.mysql;

import br.udesc.ceplan.prog3.exemplo07.dao.ClienteDao;
import br.udesc.ceplan.prog3.exemplo07.dao.DaoFactory;
import br.udesc.ceplan.prog3.exemplo07.dao.PedidoDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class MysqlDaoFactory extends DaoFactory {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Connection connection;

    public MysqlDaoFactory(String uri, String login, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        this.connection = DriverManager.getConnection(uri, login, password);
        
    }

    @Override
    public void startTransaction() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void commitTransaction() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rollbackTransaction() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClienteDao getClienteDao() {
        return new MysqlClienteDao(connection);
    }

    @Override
    public PedidoDao getPedidoDao() {
        return new MysqlPedidoDao(connection);
    }
    
}
