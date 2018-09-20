/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.job;

import br.com.park.dtbase.TBdao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.jfree.data.time.SimpleTimePeriod;

public class Caixa {

    private static int inc = 1;
    private Integer id;
    private String convenioNome;
    private float desconto;
    private float valorEntrada;/*Aqui ficará o valor que o operador 
                    entrará no caixa, que pode ser escolhido digitar ao entrar
                    ou o caixa pegará o valor do dia anterior
    */
    private float valorAcumulado;/*
                Aqui ficar o valor de lucro do caixa do dia que é igual o valor de entrada de hoje - o de ontem
   
    */
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

    public void gerarReceita(Ticket ticket) throws SQLException { //isso funciona
        banco = new TBdao();
        float valor;
        Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
        
        Instant d1 = ticket.getSaida().toInstant();
        Instant d2 = ticket.getEntrada().toInstant();
        Instant d3 = c.getTime().toInstant();
        double tempoDuracao = Duration.between(d2, d3).toMinutes();
  
        if (d1.isAfter(d3)) {
            valor = 0;
            ticket.setValor(valor);
            System.err.println("Ta liberado");
        } else {
           System.err.println(" nâo Ta liberado");
            for (TabelaPreco tb : banco.getTb()) {
                if (tempoDuracao >= tb.getTempo()) {
                    valor = tb.getMoeda();
                    ticket.setValor(valor);
                }
            }
        }
    }
}
