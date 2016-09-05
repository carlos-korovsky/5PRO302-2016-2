/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3;

import br.udesc.ceplan.prog3.ingredientes.Cobertura;
import br.udesc.ceplan.prog3.ingredientes.Massa;
import br.udesc.ceplan.prog3.ingredientes.Molho;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public interface FabricaIngredientes {
    
    public Massa criarMassa();
    public Molho criarMolho();
    public Cobertura criarCobertura(String nome);
    
}
