/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.sbs;

import br.udesc.ceplan.prog3.Pizza;
import br.udesc.ceplan.prog3.PizzaStore;
import br.udesc.ceplan.prog3.sbs.pizza.PizzaPalmito;
import br.udesc.ceplan.prog3.sbs.pizza.PizzaQueijo;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class SBSPizzaStore extends PizzaStore {
    
    private final SBSFabricaIngredientes fabIngredientes;
    
    public SBSPizzaStore() {
        this.fabIngredientes = new SBSFabricaIngredientes();
    }

    @Override
    protected Pizza createPizza(String sabor) {
        Pizza pizza = null;
        if (sabor.equalsIgnoreCase("Queijo")) {
            pizza = new PizzaQueijo(this.fabIngredientes);
        } else if (sabor.equalsIgnoreCase("Palmito")) {
            pizza = new PizzaPalmito(this.fabIngredientes);
        }
        return pizza;
    }
    
}
