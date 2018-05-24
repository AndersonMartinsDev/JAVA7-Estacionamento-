package br.com.park.job;

import java.awt.Choice;
import java.awt.Label;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JOptionPane;

public class Ticket {

    private static int inc = 0;
    private int id;
    private int codigo;
    private Date entrada;
    private Date saida;
    private String status;
    private float valor;
    private Servicos serv;
    // Observar se é necessario criar apenas uma instancia do calendário aqui. 

    public Ticket() {
        this.id = inc++;
        setId(id);
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

    public String buscaStatus() {
        String sts;
        Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
        Instant d1 = getSaida().toInstant();
        Instant d3 = c.getTime().toInstant();

        int ns = (int) Duration.between(d3, d1).toMinutes();

        // Metodo para Mostrar o status 
        if (ns < 0) {
           sts = "Tempo Excedido";
        } else {
            sts ="Liberado → " + ns + " minutos";   
        }
        return sts;
    }

    public int gerarCodigo() {
        double codigoTicket = Math.random() * 100000;
        int valorAleatorio = (int) Math.round(codigoTicket);
        setCodigo(valorAleatorio);
        return valorAleatorio;
    }

    public void novoTicket(Ticket bilhete) {
        //Fazer o código ser gerado automaticamente
        bilhete.setCodigo(gerarCodigo());
        //Pega hora atual
        bilhete.setEntrada(setaHoraAtual());
        //Pega hora da entrada e soma com tolerancia de serviços
        bilhete.setSaida(saida);
        //Status também será automatico
        bilhete.setStatus(status);
    }

    public String conversorHora() {
        // Aqui vai retornar um String
        DateFormat forma = DateFormat.getDateInstance();
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        // c.before(horaSaida);
        return String.valueOf(forma.format(date));
    }

    public Date setaHoraSaida(int tolerancia) {

        //Lembrar de setar dentro dos metodos corretos setSAIDA setENTRADA
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
