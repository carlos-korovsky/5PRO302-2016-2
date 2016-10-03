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
package br.udesc.ceplan.prog3.exemplo07.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface genérica para mapeamento de relações.
 * Contém os métodos básicos para manipulação dos dados em um SGBD
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 * @param <E> Objeto que representa a entidade (relação) no banco de dados
 * @param <K> Objeto que representa a chave primária da relação.
 */
public interface GenericDao <E, K extends Serializable> {
    
    /**
     * Salva uma nova entidade no banco de dados
     * @param tupla entidade a ser criada no banco de dados
     * @return entidade criada no banco de dados
     */
    public E create(E tupla) ;
    
    /**
     * Retorna uma tupla do banco de dados através da chave primária
     * @param id identificador da tubpla na relação (chave primária)
     * @return tupla encontrada no banco de dados ou null caso não encontrada
     */
    public E retrieve(K id);
    
    /**
     * Retorna todas as tuplas da entidade
     * @return lista contendo todas as entidades do banco
     */
    public List<E> retrieveAll();
    
    /**
     * Atualiza uma tupla no banco de dados
     * @param tupla a ser atualizada no banco de dados
     * @return tupla atualizada no banco de dados
     */
    public E update(E tupla);
    
    /**
     * Excluí uma tupla da relação
     * @param id identificador da tupla na relação
     * @return tupla excluída na relação ou null caso não encontrada
     */    
    public E delete(K id);
    
    /**
     * Excluí uma tupla da relação
     * @param tupla valor da tupla a ser excluída na relação
     * @return tupla excluída na relação ou null caso não encontrada
     */
    public E delete(E tupla);
    
}
