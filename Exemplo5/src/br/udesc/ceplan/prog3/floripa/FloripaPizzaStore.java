/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.floripa;

import br.udesc.ceplan.prog3.Pizza;
import br.udesc.ceplan.prog3.PizzaStore;
import br.udesc.ceplan.prog3.floripa.pizza.PizzaSaturno;
import br.udesc.ceplan.prog3.floripa.pizza.PizzaTerra;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class FloripaPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String sabor) {
        Pizza pizza = null;
        if (sabor.equalsIgnoreCase("Queijo")) {
            pizza = new PizzaTerra();
        } else if (sabor.equalsIgnoreCase("Palmito")) {
            pizza = new PizzaSaturno();
        }
        return pizza;
    }
    
}
