/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo9.dao;

import br.udesc.ceplan.prog3.exemplo9.model.Company;
import java.util.Set;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public interface CompanyDao extends ICrud<Company, Long> {
    
    public Set<Company> findByName(String name);
    
}
