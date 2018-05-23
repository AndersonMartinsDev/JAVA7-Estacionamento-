/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.job;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Caixa {

    private static int inc = 1;
    private Integer id;
    private String convenioNome;
    private boolean cobrar;
    private float valor;
    private float desconto;
    private TabelaPreco tb;

    public Caixa() {
        this.id = inc++;
        setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConvenioNome() {
        return convenioNome;
    }

    public void setConvenioNome(String convenioNome) {
        this.convenioNome = convenioNome;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public boolean isCobrar() {
        return cobrar;
    }

    public void setCobrar(boolean cobrar) {
        this.cobrar = cobrar;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void gerarReceita(Ticket tk) {
        tb = new TabelaPreco();
        
        tb.getMoeda();
        
        Caixa cx = new Caixa();
        
        cx.setCobrar(true);
        cx.setValor(0);
        
    }

}
