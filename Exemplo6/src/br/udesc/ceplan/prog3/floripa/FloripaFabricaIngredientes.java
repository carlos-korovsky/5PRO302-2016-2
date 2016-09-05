/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.floripa;

import br.udesc.ceplan.prog3.FabricaIngredientes;
import br.udesc.ceplan.prog3.floripa.ingredientes.FloripaCoberturaCatupiry;
import br.udesc.ceplan.prog3.floripa.ingredientes.FloripaCoberturaChampignon;
import br.udesc.ceplan.prog3.floripa.ingredientes.FloripaCoberturaMussarela;
import br.udesc.ceplan.prog3.floripa.ingredientes.FloripaCoberturaPalmito;
import br.udesc.ceplan.prog3.floripa.ingredientes.FloripaMassa;
import br.udesc.ceplan.prog3.floripa.ingredientes.FloripaMolho;
import br.udesc.ceplan.prog3.ingredientes.Cobertura;
import br.udesc.ceplan.prog3.ingredientes.Massa;
import br.udesc.ceplan.prog3.ingredientes.Molho;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class FloripaFabricaIngredientes implements FabricaIngredientes {

    @Override
    public Massa criarMassa() {
        return new FloripaMassa();
    }

    @Override
    public Molho criarMolho() {
        return new FloripaMolho();
    }

    @Override
    public Cobertura criarCobertura(String nome) {
        Cobertura cobertura = null;
        if (nome.equalsIgnoreCase("palmito")) {
            cobertura = new FloripaCoberturaPalmito();
        } else if (nome.equalsIgnoreCase("champignon")) {
            cobertura = new FloripaCoberturaChampignon();
        } else if (nome.equalsIgnoreCase("mussarela")) {
            cobertura = new FloripaCoberturaMussarela();
        } else if (nome.equalsIgnoreCase("catupiry")) {
            cobertura = new FloripaCoberturaCatupiry();
        }
        return cobertura;
    }

    
}
