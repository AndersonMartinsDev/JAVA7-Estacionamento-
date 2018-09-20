package br.com.park.job;

import java.awt.Color;
import java.text.DateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JLabel;

public class Ticket {

    private static int inc = 0;
    private int id;
    private int codigo;
    private Date entrada;
    private Date saida;
    private String status;
    private float valor;
    private String serv;
    private Caixa cx;
    // Observar se é necessario criar apenas uma instancia do calendário aqui. 

    public Ticket() {

    }

    public String getServ() {
        return serv;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    public Caixa getCx() {
        return cx;
    }

    public void setCx(Caixa cx) {
        this.cx = cx;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //------------------------------------------------------
    public String buscaStatus(JLabel j1) {
        
            Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
            Instant d1 = getSaida().toInstant();
            Instant d2 = this.getEntrada().toInstant();
            Instant d3 = c.getTime().toInstant();

            double ns = (double) Duration.between(d3, d1).toMinutes();
            double tempoDuracao = (double) Duration.between(d2, d3).toMinutes();

            if (ns == 0) {
                j1.setForeground(Color.blue);
                return "Seu tempo esgotou: " + (int) ns;
            } else if (true) {
                //d1.isAfter(d3)
                j1.setForeground(Color.magenta);
                return "Liberado → " + (int) ns + " Minutos";

            } else {
                j1.setForeground(Color.red);
                return "Excedeu: " + (int) -ns + " Minutos";
            }
       
    }

    public int gerarCodigo() {
        double codigoTicket = Math.random() * 100000;
        int valorAleatorio = (int) Math.round(codigoTicket);
        setCodigo(valorAleatorio);
        return valorAleatorio;
    }

    public Date setaHoraSaida(int tolerancia) {
        Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
        c.setTime(getEntrada());
        c.add(Calendar.MINUTE, tolerancia);
        Date horaSaida = c.getTime();
        setSaida(horaSaida);
        return horaSaida;
    }

    public Date setaHoraAtual() {
        Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
        Date horaAtual = c.getTime();
        setEntrada(horaAtual);
        return horaAtual;
    }

    public Date setaHora(int hora, int minuto, int segundo) {
        Calendar setHora = GregorianCalendar.getInstance(Locale.ROOT);
        setHora.set(Calendar.HOUR_OF_DAY, hora);
        setHora.set(Calendar.MINUTE, minuto);
        setHora.set(Calendar.SECOND, segundo);
       
        setEntrada(setHora.getTime());
        return setHora.getTime();
    }

    public Date setaData(int dia, int mes, int ano, int hora, int minuto) {
        Calendar setData = Calendar.getInstance(Locale.ROOT);
        setData.set(Calendar.DATE, dia);
        setData.set(Calendar.MONTH, mes - 1);
        setData.set(Calendar.YEAR, ano);
        setData.set(Calendar.HOUR_OF_DAY, hora);
        setData.set(Calendar.MINUTE, minuto);
        return setData.getTime();
    }
    public Ticket gerarTicket(String servicoNome, int tolerancia){
       
        Ticket passe = new Ticket();
        passe.gerarCodigo();
        passe.setaHoraAtual();
        passe.setaHoraSaida(tolerancia);
        passe.setServ(servicoNome);
        
           return passe;
       }

    public String toString() {
        String tx;
        tx = "Calcular tempo que falta pra sair" + "\n"
                + "ID: " + getId() + "\n"
                + "Codigo: " + (int) getCodigo() + "\n"
                + "Entrada: " + getEntrada() + "\n"
                + "Saida: " + getSaida();
        return tx;
    }
}
