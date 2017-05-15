/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo9;

import java.util.Properties;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class Configuration {
    
    public enum DatabaseType {
        UNKNOWN, HSQL, MYSQL, POSTGRESQL;
    }
    
    private static Configuration instance;
    
    private DatabaseType databaseType;
    private String databaseHost;
    private Integer databasePort;
    private String databaseName;
    private String databaseUser;
    private String databasePassword;
    
    
    private Configuration() {
        this.databaseType = DatabaseType.UNKNOWN;
        this.databaseHost = null;
        this.databasePort = null;
        this.databaseName = null;
        this.databaseUser = null;
        this.databasePassword = null;
    }
    
    public static synchronized Configuration getInstance() {
        if (Configuration.instance == null) {
            Configuration.instance = new Configuration();
        }
        return Configuration.instance;
    }
    
    public void loadFromProperties(Properties properties) {
        String dbType = properties.getProperty("database", "unkown");
        if (dbType.equalsIgnoreCase("HSQL")) {
            this.databaseType = DatabaseType.HSQL;
        } else if (dbType.equalsIgnoreCase("MYSQL")) {
            this.databaseType = DatabaseType.MYSQL;
        } else if (dbType.equalsIgnoreCase("POSTGRESQL")) {
            this.databaseType = DatabaseType.POSTGRESQL;
        }
        String dbHost = properties.getProperty("host", null);
        if (dbHost != null) {
            this.databaseHost = dbHost;
        }
        String dbPort = properties.getProperty("port", null);
        if (dbPort != null) {
            this.databasePort = Integer.parseInt(dbPort);
        }
        String dbName = properties.getProperty("name", null);
        if (dbName != null) {
            this.databaseName = dbName;
        }
        String dbUser = properties.getProperty("user", null);
        if (dbUser != null) {
            this.databaseUser = dbUser;
        }
        String dbPassword = properties.getProperty("password", null);
        if (dbPassword != null) {
            this.databasePassword = dbPassword;
        }
    }
    
    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public Integer getDatabasePort() {
        return databasePort;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }    
}
