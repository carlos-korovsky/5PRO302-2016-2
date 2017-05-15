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
package br.udesc.ceplan.prog3.exemplo07.dao;

import br.udesc.ceplan.prog3.exemplo07.config.Configuration;
import br.udesc.ceplan.prog3.exemplo07.config.DatabaseType;
import br.udesc.ceplan.prog3.exemplo07.dao.hsqldb.HsqldbDaoFactory;
import br.udesc.ceplan.prog3.exemplo07.dao.mysql.MysqlDaoFactory;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public abstract class DaoFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(DaoFactory.class);
    private static DaoFactory instance;
    
    protected DaoFactory() {
        
    }
    
    public static DaoFactory getFactory() throws SQLException, ClassNotFoundException {
        if (DaoFactory.instance == null) {
            int dataBaseType = Configuration.getInstance().getDatabaseType();
            if (dataBaseType == DatabaseType.DATABASE_HSQLDB) {
                DaoFactory.instance = new HsqldbDaoFactory("jdbc:hsqldb:mem:aname", "sa", "");
            } else if (dataBaseType == DatabaseType.DATABASE_MYSQL) {
                DaoFactory.instance = new MysqlDaoFactory("jdbc:mysql:..", "root", "");
            } else {
                DaoFactory.instance = null;
            }
            
        }
        return DaoFactory.instance;
    }
    
    public abstract void startTransaction() throws SQLException;
    public abstract void commitTransaction() throws SQLException;
    public abstract void rollbackTransaction() throws SQLException;
    
    public abstract ClienteDao getClienteDao();
    public abstract PedidoDao getPedidoDao();
}
