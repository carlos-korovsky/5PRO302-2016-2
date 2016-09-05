/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3;

import java.util.List;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public abstract class Pizza {
    
    private String nome;
    private String massa;
    private String molho;
    private List<String> coberturas;

    public Pizza() {
        this.definir();
    }
    
    protected abstract void definir();
    
    public void preparar() {
        System.out.println("Preparando pizza " + nome);
        System.out.println("   Preparando massa: " + massa);
        System.out.println("   Adicionando molho: " + molho);
        System.out.println("   Adicionando coberturas: ");
        for (String cobertura: this.coberturas) {
            System.out.println("       -> " + cobertura);
        }
        System.out.println("Pizza " + nome + " preparada!");
    }
    
    public void cozinhar() {
        System.out.println("Levando a pizza ao forno por 25 minutos a 350o.C");
    }
    
    public void cortar() {
        System.out.println("Cortando a pizza em pedaços diagonais");
    }
    
    public void embalar() {
        System.out.println("Embalando a pizza na caixa oficial");
    }

    public void entregar() {
        System.out.println("Engregando pizza: " + nome);
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMassa(String massa) {
        this.massa = massa;
    }

    public void setMolho(String molho) {
        this.molho = molho;
    }

    public void setCoberturas(List<String> coberturas) {
        this.coberturas = coberturas;
    }
    
    
}
