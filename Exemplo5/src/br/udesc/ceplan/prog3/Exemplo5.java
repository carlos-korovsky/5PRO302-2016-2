/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3;

import br.udesc.ceplan.prog3.floripa.FloripaPizzaStore;
import br.udesc.ceplan.prog3.sbs.SBSPizzaStore;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class Exemplo5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pizza pizza;
        PizzaStore sbsPizzaStore = new SBSPizzaStore();
        PizzaStore floripaPizzaStore = new FloripaPizzaStore();
        
        pizza = sbsPizzaStore.orderPizza("queijo");       
        pizza = floripaPizzaStore.orderPizza("queijo");
        
        pizza = sbsPizzaStore.orderPizza("palmito");
        pizza = floripaPizzaStore.orderPizza("palmito");
    }
    
}
