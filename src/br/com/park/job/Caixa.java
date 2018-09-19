/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.job;

import br.com.park.dtbase.bdBack;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import br.com.park.job.Ticket;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Caixa {

    private static int inc = 1;
    private Integer id;
    private String convenioNome;
    private float desconto;
    private bdBack banco;

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

    public void gerarReceita(Ticket ticket) {
        banco = new bdBack();
        float valor;
        Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
        Instant d1 = ticket.getSaida().toInstant();
        Instant d2 = ticket.getEntrada().toInstant();
        Instant d3 = c.getTime().toInstant();
        double tempoDuracao = (double) Duration.between(d2, d3).toMinutes();
        if (d1.isAfter(d3)) {
            valor = 0;
            ticket.setValor(valor);
        } else {
            for (int i = 0; i < banco.getBdTabPreco().size(); i++) {
                if (tempoDuracao >= banco.getBdTabPreco().get(i).getTempo()) {   
                  valor = banco.getBdTabPreco().get(i).getMoeda();
                  ticket.setValor(valor);
                }
            }
        }
    }
    /**
    public float gerarReceita(Ticket ticket) {
        float receita = 0;
        Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
        Instant d1 = ticket.getSaida().toInstant();
        Instant d3 = c.getTime().toInstant();
        int ns = (int) Duration.between(d3, d1).toMinutes();
        // Metodo para Mostrar o status 
        
        for (int i = 0; i < banco.getBdTabPreco().size(); i++) {
            if (-ns >= banco.getBdTabPreco().get(i).getTempo()) {
                receita = banco.getBdTabPreco().get(i).getMoeda();
                /*Deve-se pegar o ticket e atualizar o valor de acordo com o tempo
                setando no setValor() do ticket. isso será setado no retorno
                
                *//*
            }
        }
        return receita;
    }*/
    
    public void attReceita(){
        banco = new bdBack();
        for (int i = 0; i < banco.getBdTicket().size(); i++) {
            gerarReceita(banco.getBdTicket().get(i));
        }
    }
}

