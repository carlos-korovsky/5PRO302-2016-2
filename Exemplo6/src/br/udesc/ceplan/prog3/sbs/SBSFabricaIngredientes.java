/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.sbs;

import br.udesc.ceplan.prog3.FabricaIngredientes;
import br.udesc.ceplan.prog3.ingredientes.Cobertura;
import br.udesc.ceplan.prog3.ingredientes.Massa;
import br.udesc.ceplan.prog3.ingredientes.Molho;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class SBSFabricaIngredientes implements FabricaIngredientes {

    @Override
    public Massa criarMassa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Molho criarMolho() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cobertura criarCobertura(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
