/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo9;

import br.udesc.ceplan.prog3.exemplo9.dao.ClientDao;
import br.udesc.ceplan.prog3.exemplo9.dao.CompanyDao;
import br.udesc.ceplan.prog3.exemplo9.dao.DaoFactory;
import br.udesc.ceplan.prog3.exemplo9.model.Client;
import br.udesc.ceplan.prog3.exemplo9.model.Company;
import java.util.Properties;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class Exemplo9 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Carrega as configurações de conexão ao banco de dados
        Properties configFile = new Properties();
        configFile.setProperty("database", "HSQL");
        configFile.setProperty("host", "");
        configFile.setProperty("port", "");
        configFile.setProperty("name", "testedb");
        configFile.setProperty("user", "SA");
        configFile.setProperty("password", "");
        
        Configuration config = Configuration.getInstance();
        config.loadFromProperties(configFile); //Configura o sistema
        
        DaoFactory dao = DaoFactory.getInstance();
        
        ClientDao clientDao = dao.getClientDao();

        Client newClient;
        newClient = new Client();
        newClient.setName("Cliente 01");
        newClient.setCpf("123456789");
        newClient = clientDao.save(newClient);       
        System.out.println(newClient);

        newClient = new Client();
        newClient.setName("Cliente 02");
        newClient.setCpf("012345678");
        newClient = clientDao.save(newClient);       
        System.out.println(newClient);


        CompanyDao companyDao = dao.getCompanyDao();
        Company newCompany;
        newCompany = new Company();
        newCompany.setName("Empresa 01");
        newCompany.setCnpj("4567890132");
        newCompany = companyDao.save(newCompany);
        System.out.println(newCompany);
        
        newCompany = new Company();
        newCompany.setName("Empresa 02");
        newCompany.setCnpj("7890132345");
        newCompany = companyDao.save(newCompany);
        System.out.println(newCompany);
        
    }
    
}
