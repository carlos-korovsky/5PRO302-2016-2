/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.exemplo08;



/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public class Exemplo08 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MenuComponent menuBase = new Menu("Menu base", "Todos os menus");
        MenuComponent menuCafeManha = new Menu("Menu Café", "Menu de café da manhã");
        menuCafeManha.add(new MenuItem("Café", "Café preto", 1.00));
        menuCafeManha.add(new MenuItem("Café com leite", "Café com leite", 2.00));
        menuBase.add(menuCafeManha);
        MenuComponent menuAlmoco = new Menu("Menu Almoço", "Menu de almoço");
        menuAlmoco.add(new MenuItem("Bife a cavalo", "Bife a cavalo", 10.00));
        menuAlmoco.add(new MenuItem("Bife a parmegiana", "Bife a parmegiana", 20.00));
        menuBase.add(menuAlmoco);
        menuBase.print();
    }
    
}
