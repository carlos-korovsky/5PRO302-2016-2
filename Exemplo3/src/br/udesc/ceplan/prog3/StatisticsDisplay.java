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
public class StatisticsDisplay implements Observer, DisplayElement {
    
    private final Observable subject;
    private float temperaturaMax;
    private float temperaturaMin;
    private float temperaturaAvg;
    
    public StatisticsDisplay(Observable subject) {
        this.subject = subject;
        this.subject.addObserver(this);
        this.temperaturaMax = Float.MIN_VALUE;
        this.temperaturaMin = Float.MAX_VALUE;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData wd = (WeatherData) o;
            float temperatura = wd.getTemperatura();
            if (temperatura > temperaturaMax) {
                temperaturaMax = temperatura;
            }
            if (temperatura < temperaturaMin) {
                temperaturaMin = temperatura;
            }
            this.temperaturaAvg = (this.temperaturaMax + this.temperaturaMin) / 2;
            this.display();
        }
    }

    @Override
    public void display() {
        System.out.println("Estatísticas de temperatura:");
        System.out.format ("   Máxima: %f\n", this.temperaturaMax);
        System.out.format ("   Mínima: %f\n", this.temperaturaMin);
        System.out.format ("    Média: %f\n", this.temperaturaAvg);
        System.out.println("");
    }
    
}
