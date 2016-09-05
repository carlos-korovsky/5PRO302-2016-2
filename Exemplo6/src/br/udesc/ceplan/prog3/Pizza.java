/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3;

import br.udesc.ceplan.prog3.ingredientes.Cobertura;
import br.udesc.ceplan.prog3.ingredientes.Massa;
import br.udesc.ceplan.prog3.ingredientes.Molho;
import java.util.List;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky at gmail.com>
 */
public abstract class Pizza {
    
    private String nome;
    private Massa massa;
    private Molho molho;
    private List<Cobertura> coberturas;
    
    private final FabricaIngredientes ingredientes;

    public Pizza(FabricaIngredientes ingredientes) {
        this.ingredientes = ingredientes;
    }
        
    protected FabricaIngredientes getIngredientes() {
        return this.ingredientes;
    }
    
    protected abstract void separarIngredientes();
    
    public void preparar()  {
        this.separarIngredientes();
        System.out.println("Preparando pizza " + nome);
        System.out.println("   Preparando massa: " + massa.getMassa());
        System.out.println("   Adicionando molho: " + molho.getMolho());
        System.out.println("   Adicionando coberturas: ");
        for (Cobertura cobertura: this.coberturas) {
            System.out.println("       -> " + cobertura.getCobertura());
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

    public final String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
        this.nome = nome;
    }

    public Massa getMassa() {
        return massa;
    }

    public void setMassa(Massa massa) {
        this.massa = massa;
    }

    public Molho getMolho() {
        return molho;
    }

    public void setMolho(Molho molho) {
        this.molho = molho;
    }

    public List<Cobertura> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<Cobertura> coberturas) {
        this.coberturas = coberturas;
    }
    
}
