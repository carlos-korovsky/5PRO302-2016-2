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
package br.udesc.ceplan.prog3.exemplo07.dao.hsqldb;

import br.udesc.ceplan.prog3.exemplo07.dao.ClienteDao;
import br.udesc.ceplan.prog3.exemplo07.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class HsqldbClienteDao implements ClienteDao {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private final Connection connection;

    public HsqldbClienteDao(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public List<Cliente> findByName(String name) {
        String sql;
        sql = "select id, nome " +
              "from cliente" +
              "where (nome like ?)";
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%"+name+"%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getLong("id"));
                    cliente.setNome(rs.getString("nome"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getLocalizedMessage());
        }
        return clientes;
    }

    @Override
    public Cliente create(Cliente tupla) {
        String sql;
        sql = "INSERT INTO cliente (nome)" +
              "VALUES (?)";
        Cliente cliente = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql,
                                      Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tupla.getNome());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tupla.setId(generatedKeys.getLong(1));
                    cliente = tupla;
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getLocalizedMessage());
        }
        return cliente;
    }

    @Override
    public Cliente retrieve(Long id) {
        String sql;
        sql = "select id, nome " +
              "from cliente" +
              "where (id = ?)";
        Cliente cliente = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getLong("id"));
                    cliente.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getLocalizedMessage());
        }
        return cliente;
    }

    @Override
    public List<Cliente> retrieveAll() {
        String sql;
        sql = "select id, nome " +
              "from cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getLong("id"));
                    cliente.setNome(rs.getString("nome"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getLocalizedMessage());
        }
        return clientes;
    }

    @Override
    public Cliente update(Cliente tupla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente delete(Cliente tupla) {
        return this.delete(tupla.getId());
    }
    
}
