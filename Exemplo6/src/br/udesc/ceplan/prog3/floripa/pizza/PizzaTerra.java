/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.floripa.pizza;

import br.udesc.ceplan.prog3.FabricaIngredientes;
import br.udesc.ceplan.prog3.Pizza;
import br.udesc.ceplan.prog3.ingredientes.Cobertura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class PizzaTerra extends Pizza {

    public PizzaTerra(FabricaIngredientes ingredientes) {
        super(ingredientes);
        this.setNome("Pizza Terra");
    }

    @Override
    protected void separarIngredientes() {
        FabricaIngredientes ingredientes = this.getIngredientes();
        this.setMassa(ingredientes.criarMassa());
        this.setMolho(ingredientes.criarMolho());
        List<Cobertura> coberturas = new ArrayList<>();
        coberturas.add(ingredientes.criarCobertura("mussarela"));
        coberturas.add(ingredientes.criarCobertura("catupiry"));
        this.setCoberturas(coberturas);
    }    
}
