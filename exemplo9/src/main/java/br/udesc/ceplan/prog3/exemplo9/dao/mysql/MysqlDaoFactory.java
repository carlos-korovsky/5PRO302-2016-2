/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo9.dao.mysql;

import br.udesc.ceplan.prog3.exemplo9.dao.ClientDao;
import br.udesc.ceplan.prog3.exemplo9.dao.CompanyDao;
import br.udesc.ceplan.prog3.exemplo9.dao.DaoFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class MysqlDaoFactory extends DaoFactory {

    @Override
    protected void createDb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientDao getClientDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyDao getCompanyDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
