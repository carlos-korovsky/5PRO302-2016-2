/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo9.dao;

import br.udesc.ceplan.prog3.exemplo9.Configuration;
import br.udesc.ceplan.prog3.exemplo9.dao.hsql.HsqlDaoFactory;
import br.udesc.ceplan.prog3.exemplo9.dao.mysql.MysqlDaoFactory;
import br.udesc.ceplan.prog3.exemplo9.dao.postgresql.PostgresqlDaoFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public abstract class DaoFactory {
    
    private static DaoFactory instance;
    
    private Connection dbConnection;
    
    protected DaoFactory() {
        this.dbConnection = null;
    }
    
    public static synchronized DaoFactory getInstance() {
        StringBuilder databaseUrl = new StringBuilder("jdbc:");
        
        if (DaoFactory.instance == null) {
            Configuration config;
            config = Configuration.getInstance();
            try {
                switch (config.getDatabaseType()) {
                    case HSQL:
                        Class.forName("org.hsqldb.jdbc.JDBCDriver" );
                        databaseUrl.append("hsqldb:");
                        databaseUrl.append("mem:");
                        databaseUrl.append(config.getDatabaseName());
                        DaoFactory.instance = new HsqlDaoFactory();
                        break;
                    case MYSQL:
                        Class.forName("com.mysql.jdbc.Driver" );
                        databaseUrl.append("mysql:");
                        databaseUrl.append("//").append(config.getDatabaseHost());
                        databaseUrl.append(":").append(config.getDatabasePort());
                        databaseUrl.append("/").append(config.getDatabaseName());
                        DaoFactory.instance = new MysqlDaoFactory();
                        break;
                    case POSTGRESQL:
                        Class.forName("org.postgresql.Driver" );
                        databaseUrl.append("postgresql:");
                        databaseUrl.append("//").append(config.getDatabaseHost());
                        databaseUrl.append(":").append(config.getDatabasePort());
                        databaseUrl.append("/").append(config.getDatabaseName());
                        DaoFactory.instance = new PostgresqlDaoFactory();
                        break;
                    default:
                        throw new RuntimeException("Tipo de banco de dados não definido");
                }
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("Driver do banco de dados não encontrado", ex);
            }
            if (DaoFactory.instance != null) {
                try {
                    Connection conn;
                    conn = DriverManager.getConnection(databaseUrl.toString(), config.getDatabaseUser(), config.getDatabasePassword());
                    DaoFactory.instance.setDatabaseConnection(conn);
                    DaoFactory.instance.createDb();
                } catch (SQLException ex) {
                    DaoFactory.instance = null;
                    throw new RuntimeException("Erro ao conectar no banco de dados", ex);
                }
            }
        }
        return DaoFactory.instance;
    }
    
    protected Connection getDbConnection() {
        return this.dbConnection;
    }
    
    protected void setDatabaseConnection(Connection conn) {
        this.dbConnection = conn;
    }
    
    protected abstract void createDb();
    
    public abstract ClientDao getClientDao();
    
    public abstract CompanyDao getCompanyDao();
    
}
