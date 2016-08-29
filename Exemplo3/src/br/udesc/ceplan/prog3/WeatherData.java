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

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class WeatherData extends Observable {
    private float temperatura;
    private float umidade;
    private float pressao;
    
    public void medidasAlteradas() {
        this.setChanged();
        this.notifyObservers();
    }
    
    public void setMedidas(Float temperatura, Float umidade, Float pressao) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.pressao = pressao;
        this.medidasAlteradas();
    }

    public float getTemperatura() {
        return temperatura;
    }

    public float getUmidade() {
        return umidade;
    }

    public float getPressao() {
        return pressao;
    }
    
}
