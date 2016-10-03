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
import br.udesc.ceplan.prog3.sbs.ingredientes.SBSCoberturaMussarela;
import br.udesc.ceplan.prog3.sbs.ingredientes.SBSCoberturaPalmito;
import br.udesc.ceplan.prog3.sbs.ingredientes.SBSMassa;
import br.udesc.ceplan.prog3.sbs.ingredientes.SBSMolho;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class SBSFabricaIngredientes implements FabricaIngredientes {

    @Override
    public Massa criarMassa() {
        return new SBSMassa();
    }

    @Override
    public Molho criarMolho() {
        return new SBSMolho();
    }

    @Override
    public Cobertura criarCobertura(String nome) {
        Cobertura cobertura = null;
        if (nome.equalsIgnoreCase("palmito")) {
            cobertura = new SBSCoberturaPalmito();
        } else if (nome.equalsIgnoreCase("mussarela")) {
            cobertura = new SBSCoberturaMussarela();
        }
        return cobertura;
    }
    
}
