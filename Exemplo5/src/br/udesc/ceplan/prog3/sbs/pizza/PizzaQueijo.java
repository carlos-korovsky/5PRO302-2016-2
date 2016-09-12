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
public class PizzaQueijo extends Pizza {

    public PizzaQueijo() {
        super("Pizza de queijo");
    }

    @Override
    public void separarIngredientes() {
        this.setMassa("grossa");
        this.setMolho("molho de tomate");
        List<String> coberturas = new ArrayList<>();
        coberturas.add("mussarela");
        this.setCoberturas(coberturas);
    }
}