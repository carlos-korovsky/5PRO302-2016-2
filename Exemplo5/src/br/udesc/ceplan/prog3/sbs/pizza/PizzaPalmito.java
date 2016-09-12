/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.sbs.pizza;

import br.udesc.ceplan.prog3.Pizza;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class PizzaPalmito extends Pizza {

    public PizzaPalmito() {
        super("Pizza de palmito");
    }
    
    @Override
    public void separarIngredientes() {
        this.setMassa("grossa");
        this.setMolho("molho de tomate");
        List<String> coberturas = new ArrayList<>();
        coberturas.add("mussarela");
        coberturas.add("palmito");
        this.setCoberturas(coberturas);
    }
}
