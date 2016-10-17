/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo08;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class Menu extends MenuComponent {
    private List<MenuComponent> menuComponents;
    private String name;
    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
        this.menuComponents = new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void print() {
        System.out.print("MENU:  " + this.getName());
        System.out.println(",  " + this.getDescription());
        System.out.println("X---------------------------------------X");
        for (MenuComponent menuComponent: this.menuComponents) {
            menuComponent.print();
        }
        System.out.println("-----------------------------------------");
    }

    @Override
    public MenuComponent getChild(int i) {
        return this.menuComponents.get(i);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        this.menuComponents.remove(menuComponent);
    }

    @Override
    public void add(MenuComponent menuComponent) {
        this.menuComponents.add(menuComponent);
    }
    
}
