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
public class WeatherStation {
    
    private static WeatherStation application;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WeatherStation.application = new WeatherStation();
        WeatherStation.application.start();
    }
    
    private void start() {
        WeatherData data = new WeatherData();
        
        CurrentConditionsDisplay current = new CurrentConditionsDisplay(data);
        StatisticsDisplay estatisticas = new StatisticsDisplay(data);
        
        System.out.println("Leitura 01");
        data.setMedidas(25f, 65f, 30.4f);
        System.out.println("Leitura 02");
        data.setMedidas(28f, 70f, 29.2f);
        System.out.println("Leitura 03");
        data.setMedidas(20f, 90f, 28.7f);
        
        data.removeObserver(current);
        data.removeObserver(estatisticas);
    }
    
}
