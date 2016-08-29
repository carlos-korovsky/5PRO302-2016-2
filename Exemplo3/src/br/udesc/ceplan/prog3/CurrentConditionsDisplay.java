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

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    
    private final Observable subject;
    private float temperatura;
    private float umidade;
    private float pressao;

    public CurrentConditionsDisplay(Observable subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData wd = (WeatherData) o;
            this.temperatura = wd.getTemperatura();
            this.umidade = wd.getUmidade();
            this.pressao = wd.getPressao();
            this.display();
        }
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
