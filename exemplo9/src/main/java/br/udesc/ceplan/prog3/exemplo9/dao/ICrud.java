/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo9.dao;

import java.io.Serializable;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 * @param <E> Entidade de relacionamento (classe que representa a tabela no banco)
 * @param <ID> Classe que representa a chave primária da tabela
 */
public interface ICrud <E, ID extends Serializable> {
    
    public E save(E entity);
    
    public E findOne(ID id);
    
    public boolean exists(ID id);
    
    public void delete(ID id);
    
    public void delete(E entity);
    
    public void deleteAll();
    
}
