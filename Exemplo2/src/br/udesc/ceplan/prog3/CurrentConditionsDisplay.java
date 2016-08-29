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
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    
    private final Subject subject;
    private float temperatura;
    private float umidade;
    private float pressao;

    public CurrentConditionsDisplay(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }
    
    @Override
    public void update(Float temperatura, Float umidade, Float pressao) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.pressao = pressao;
        this.display();
    }

    @Override
    public void display() {
        System.out.println("Condições atuais:");
        System.out.format ("   Temperatura: %f\n", this.temperatura);
        System.out.format ("       Umidade: %f\n", this.umidade);
        System.out.format ("       Pressão: %f\n", this.pressao);
        System.out.println("");
    }
    
}
