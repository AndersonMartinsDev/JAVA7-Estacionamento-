/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.job;

import br.com.park.dtbase.TBdao;
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
    private TBdao banco;

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
        banco = new TBdao();
        float valor;
        Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
        Instant d1 = ticket.getSaida().toInstant();
        Instant d2 = ticket.getEntrada().toInstant();
        Instant d3 = c.getTime().toInstant();
        int tempoDuracao = (int) Duration.between(d2, d3).toMinutes();
        System.err.println("Chegou aqui assim\n"+ d1.toString() + " \n"+ d2.toString()+ " \n"+ d3.toString());
        
        if (d1.isAfter(d3)) {
            valor = 0;
            ticket.setValor(valor);
            System.err.println("Ta liberado");
        } else {
            for (int i = 0; i < banco.getTb().size(); i++) {
                if (tempoDuracao >= banco.getTb().get(i).getTempo()) {
                    valor = banco.getTb().get(i).getMoeda();
                    ticket.setValor(valor);

                }

                System.err.println(" nâo Ta liberado");
            }
        }
    }
    /**
     *
     * public void attReceita(){ banco = new bdBack(); for (int i = 0; i <
     * banco.getBdTicket().size(); i++) {
     * gerarReceita(banco.getBdTicket().get(i)); }
    }
     */
}
