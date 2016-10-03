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
package br.udesc.ceplan.prog3.exemplo07.config;

import java.util.Properties;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class Configuration {
    
    private static Configuration instance;
    private int databaseType;
    
    private Configuration() {
        databaseType = DatabaseType.DATABASE_UNKNOWN;
    }
    
    public static Configuration getInstance() {
        if (Configuration.instance == null) {
            Configuration.instance = new Configuration();
        }
        return Configuration.instance;
    }
    
    public void loadConfiguration(Properties properties) {
        String database;
        database = properties.getProperty("database", "unknown");
        database = database.trim();
        if (database.equalsIgnoreCase("mysql")) {
            databaseType = DatabaseType.DATABASE_MYSQL;
        } else if (database.equalsIgnoreCase("hsqldb")) {
            databaseType = DatabaseType.DATABASE_HSQLDB;
        } else {
            databaseType = DatabaseType.DATABASE_UNKNOWN;
        }
    }

    public int getDatabaseType() {
        return databaseType;
    }
    
    
}
