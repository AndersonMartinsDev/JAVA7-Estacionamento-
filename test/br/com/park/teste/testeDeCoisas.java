package br.com.park.teste;

import br.com.park.dtbase.bdBack;
import br.com.park.job.Caixa;
import br.com.park.job.Estacionamento;
import br.com.park.job.Servicos;
import br.com.park.job.TabelaPreco;
import br.com.park.job.Ticket;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class testeDeCoisas {

    private static ArrayList<Servicos> Gservico = new ArrayList();

    public ArrayList<Servicos> getGservico() {
        return Gservico;
    }

    public void setGservico(ArrayList<Servicos> Gservico) {
        this.Gservico = Gservico;
    }

    public static void salvaServico(Servicos servi, String nome, int tolerancia) {
        servi.setNome(nome);
        servi.setTolerancia(tolerancia);
        testeDeCoisas.Gservico.add(servi);
    }
    public Date setaData(int dia,int mes,int ano,int hora,int minuto){
        Calendar setData = Calendar.getInstance(Locale.ROOT);
        setData.set(Calendar.DATE, dia);
        setData.set(Calendar.MONTH, mes-1);
        setData.set(Calendar.YEAR, ano);
        setData.set(Calendar.HOUR_OF_DAY, hora);
        setData.set(Calendar.MINUTE, minuto);
        return setData.getTime();
    }
 
    
    public Date setaHora(int hora,int minuto,int segundo){
        Calendar setHora = GregorianCalendar.getInstance();
        setHora.set(Calendar.HOUR_OF_DAY, hora);
        setHora.set(Calendar.MINUTE, minuto);
        setHora.set(Calendar.SECOND, segundo);
        return setHora.getTime();
    }
    
    public static void main(String args[]) throws ParseException {

        Scanner tec = new Scanner(System.in);
        /**
         * Servicos s1= new Servicos(); s1.setNome("Teste1");
         * s1.setTolerancia(40); Gservico.add(s1); Servicos s2= new Servicos();
         * s2.setNome("Teste1"); s2.setTolerancia(40); Gservico.add(s2);
         */
        /**
         * for (int i = 0; i < 3; i++) { Servicos novoServico = new Servicos();
         * System.out.println("Digite um nome para o serviço"); String nomeServ
         * = tec.nextLine(); novoServico.setNome(nomeServ);
         * System.out.println("Digite um tempo de Tolerancia"); int tolerancia =
         * tec.nextInt(); novoServico.setTolerancia(tolerancia); tec.nextLine();
         * Gservico.add(novoServico); }
         *
         * for (int i = 0; i < Gservico.size(); i++) {
         * System.out.println(Gservico.get(i).getId());
         * System.out.println(Gservico.get(i).getNome());
         * System.out.println(Gservico.size()); }
        for (int i = 0; i < 3; i++) {
            Servicos serv = new Servicos();
            System.out.println("Digite um nome para o serviço");
            String nomeServ = tec.nextLine();
            System.out.println("Digite um tempo de Tolerancia");
            int tolerancia = tec.nextInt();
            salvaServico(serv, nomeServ, tolerancia);
        }

        for (int i = 0; i < Gservico.size(); i++) {
            System.out.println(Gservico.get(i).getId());
            System.out.println(Gservico.get(i).getNome());
            System.out.println(Gservico.size());
        }
         *//*
       //Isso funciona
        DateFormat formatador = new SimpleDateFormat("HH:mm:ss");
        Calendar c = Calendar.getInstance(Locale.ROOT);
        Date horaAtual = c.getTime();
        Date horaSaida = new testeDeCoisas().setaData(06, 04, 2018, 19, 54);
             
        
        if(horaAtual.compareTo(horaSaida)<=0){
            System.err.println("pode sair\n"+horaAtual+"\n"+horaSaida+"\n");
            
        }else{
            System.err.println("Passou do Horário"+"\n"+horaAtual+"\n"+c);
        }
         
         Period period = Period.between(LocalDate.MIN, LocalDate.MAX);
         System.out.println(period.getDays());*/
         Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
         TabelaPreco tb1 = new TabelaPreco();
         Caixa cx = new Caixa();
         Ticket passe = new Ticket();
         passe.setCodigo(105202);
         
         passe.setaHora(21, 00, 00);
         passe.setaHoraSaida(40);
         
         Instant d1 = passe.getSaida().toInstant();
         Instant d2 = passe.getEntrada().toInstant();
        
         Instant d3 = c.getTime().toInstant();
       
         long ns = Duration.between(d3, d1).toMinutes();
         System.out.println(ns+" minutos");
         
         
         // Metodo para Mostrar o status 
         if(ns < 0){
             System.err.println("Tempo Excedido");
         }else{
             System.out.println("Liberado → "+ns+" minutos");
         }
         
         
    }
}
