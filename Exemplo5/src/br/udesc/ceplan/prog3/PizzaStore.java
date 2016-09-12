/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public abstract class PizzaStore {
    
    public Pizza orderPizza(String sabor) {
        System.out.println("Pedindo pizza sabor: " + sabor);
        Pizza pizza = this.createPizza(sabor);
        if (pizza != null) {
            pizza.separarIngredientes();
            pizza.preparar();
            pizza.cozinhar();
            pizza.cortar();
            pizza.embalar();
            pizza.entregar();
        } else {
            System.out.println("Sabor não disponível: " + sabor);
        }
        return pizza;
    }
    
    protected abstract Pizza createPizza(String sabor);
}
