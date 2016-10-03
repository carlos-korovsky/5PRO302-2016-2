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

import br.udesc.ceplan.prog3.exemplo07.dao.PedidoDao;
import br.udesc.ceplan.prog3.exemplo07.modelo.Cliente;
import br.udesc.ceplan.prog3.exemplo07.modelo.Pedido;
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
public class HsqldbPedidoDao implements PedidoDao {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private final Connection connection;

    public HsqldbPedidoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Pedido> findByCliente(Cliente cliente) {
        String sql;
        sql = "select id, id_cliente " +
              "from pedido " +
              "where (id_cliente = ?)";
        List<Pedido> pedidos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, cliente.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getLong("id"));
                    pedido.setCliente(cliente);
                    pedidos.add(pedido);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getLocalizedMessage());
        }
        return pedidos;
    }

    @Override
    public Pedido create(Pedido tupla) {
        String sql;
        sql = "INSERT INTO pedido (id_cliente)" +
              "VALUES (?)";
        Pedido pedido = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql,
                                      Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, tupla.getCliente().getId());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tupla.setId(generatedKeys.getLong(1));
                    pedido = tupla;
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getLocalizedMessage());
        }
        return pedido;
    }

    @Override
    public Pedido retrieve(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> retrieveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido update(Pedido tupla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido delete(Pedido tupla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
