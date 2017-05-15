/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo9.dao;

import br.udesc.ceplan.prog3.exemplo9.model.Client;
import java.util.Set;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public interface ClientDao extends ICrud<Client, Long> {
    
    public Set<Client> findByName(String name);
    
}
