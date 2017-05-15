/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo9.dao.hsql;

import br.udesc.ceplan.prog3.exemplo9.dao.ClientDao;
import br.udesc.ceplan.prog3.exemplo9.dao.CompanyDao;
import br.udesc.ceplan.prog3.exemplo9.dao.DaoFactory;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class HsqlDaoFactory extends DaoFactory {

    @Override
    protected void createDb() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE client ( ");
        sql.append("id INTEGER NOT NULL, ");
        sql.append("name VARCHAR(50), ");
        sql.append("cpf VARCHAR(15), ");
        sql.append("PRIMARY KEY(id)");
        sql.append(");");
        sql.append("CREATE TABLE company ( ");
        sql.append("id INTEGER NOT NULL, ");
        sql.append("name VARCHAR(50), ");
        sql.append("cnpj VARCHAR(18), ");
        sql.append("PRIMARY KEY(id)");
        sql.append(");");
        try {
            Statement st = this.getDbConnection().createStatement();
            st.execute(sql.toString());
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao criar statement", ex);
        }
        
    }

    @Override
    public ClientDao getClientDao() {
        return new HsqlClientDao(this.getDbConnection());
    }

    @Override
    public CompanyDao getCompanyDao() {
        return new HsqlCompanyDao(this.getDbConnection());
    }
    
}
