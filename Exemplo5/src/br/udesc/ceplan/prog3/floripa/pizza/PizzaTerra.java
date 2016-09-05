/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.floripa.pizza;

import br.udesc.ceplan.prog3.Pizza;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class PizzaTerra extends Pizza {

    @Override
    protected void definir() {
        this.setNome("Pizza Terra");
        this.setMassa("fina");
        this.setMolho("molho de tomate");
        List<String> coberturas = new ArrayList<>();
        coberturas.add("brócolis");
        coberturas.add("mussarela");
        coberturas.add("catupiry");
        coberturas.add("tomate seco");
        this.setCoberturas(coberturas);
    }
    
}
