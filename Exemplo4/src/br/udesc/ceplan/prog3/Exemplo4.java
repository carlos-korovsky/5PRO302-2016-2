/*
 * Copyright (C) 2016 Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.udesc.ceplan.prog3;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class Exemplo4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pizza calabresa = new PizzaCalabreza();
        System.out.format("Pizza: %s -> R$ %.2f\n", calabresa.getDescricao(), calabresa.getValor());
        
        Pizza calabresa2 = new PizzaCalabreza();
        calabresa2 = new BordaCatupiry(calabresa2);
        System.out.format("Pizza: %s -> R$ %.2f\n", calabresa2.getDescricao(), calabresa2.getValor());

        Pizza portuguesa = new PizzaPortuguesa();
        portuguesa = new BordaCatupiry(portuguesa);
        portuguesa = new BaconExtra(portuguesa);
        System.out.format("Pizza: %s -> R$ %.2f\n", portuguesa.getDescricao(), portuguesa.getValor());
        
        Pizza portuguesa2 = new BaconExtra(new PizzaPortuguesa());
        System.out.format("Pizza: %s -> R$ %.2f\n", portuguesa2.getDescricao(), portuguesa2.getValor());
    }
    
}
