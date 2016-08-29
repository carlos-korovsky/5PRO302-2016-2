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
public class BaconExtra extends PizzaDecorator {

    public BaconExtra(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescricao() {
        return this.getPizza().getDescricao() + " + bacon extra";
    }

    @Override
    public Double getValor() {
        return this.getPizza().getValor() + 3.50;
    }
    
}
