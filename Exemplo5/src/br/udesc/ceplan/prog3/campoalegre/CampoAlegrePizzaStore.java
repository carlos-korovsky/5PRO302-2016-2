/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.campoalegre;

import br.udesc.ceplan.prog3.Pizza;
import br.udesc.ceplan.prog3.PizzaStore;
import br.udesc.ceplan.prog3.campoalegre.pizza.PizzaPalmito;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class CampoAlegrePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String sabor) {
        Pizza pizza = null;
        if (sabor.equalsIgnoreCase("Palmito")) {
            pizza = new PizzaPalmito();
        }
        return pizza;
    }
    
}
