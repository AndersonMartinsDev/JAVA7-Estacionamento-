package br.com.park.teste;

import br.com.park.job.Servicos;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

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

    public static Date setaData(int dia, int mes, int ano, int hora, int minuto) {
        Calendar setData = Calendar.getInstance(Locale.ROOT);
        setData.set(Calendar.DATE, dia);
        setData.set(Calendar.MONTH, mes - 1);
        setData.set(Calendar.YEAR, ano);
        setData.set(Calendar.HOUR_OF_DAY, hora);
        setData.set(Calendar.MINUTE, minuto);
        return (Date) setData.getTime();
    }

    public Date setaHora(int hora, int minuto, int segundo) {
        Calendar setHora = GregorianCalendar.getInstance();
        setHora.set(Calendar.HOUR_OF_DAY, hora);
        setHora.set(Calendar.MINUTE, minuto);
        setHora.set(Calendar.SECOND, segundo);
        return (Date) setHora.getTime();
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
         * System.out.println(Gservico.size()); } for (int i = 0; i < 3; i++) {
         * Servicos serv = new Servicos(); System.out.println("Digite um nome
         * para o serviço"); String nomeServ = tec.nextLine();
         * System.out.println("Digite um tempo de Tolerancia"); int tolerancia =
         * tec.nextInt(); salvaServico(serv, nomeServ, tolerancia); }
         *
         * for (int i = 0; i < Gservico.size(); i++) {
         * System.out.println(Gservico.get(i).getId());
         * System.out.println(Gservico.get(i).getNome());
         * System.out.println(Gservico.size()); }
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
         System.out.println(period.getDays());
        Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
        bdBack bd = new bdBack();

        TabelaPreco tb1 = new TabelaPreco();
        tb1.setMoeda(4);
        tb1.setTempo(40);
        bd.salvaTbPreco(tb1);
        TabelaPreco tb2 = new TabelaPreco();
        tb2.setMoeda(8);
        tb2.setTempo(80);
        bd.salvaTbPreco(tb2);
        TabelaPreco tb3 = new TabelaPreco();
        tb3.setMoeda(12);
        tb3.setTempo(120);
        bd.salvaTbPreco(tb3);
        

        Caixa cx = new Caixa();

        Ticket passe = new Ticket();

        passe.setCodigo(105202);
        passe.setaHora(8, 40, 00);
        passe.setaHoraSaida(40);
        
        Instant d1 = passe.getSaida().toInstant();
        Instant d2 = passe.getEntrada().toInstant();
        Instant d3 = c.getTime().toInstant();

        double ns = (double) Duration.between(d2, d3).toMinutes();
       

        double tempoDuracao = (double) Duration.between(d2, d3).toMinutes();

        
        /*Aqui verifica se a hora de saida está depois da hora atual.
         Se estiver não cobrar 
         Se não estiver cobrar de acordo com a tabela de preço
         *//*
        if (d1.isAfter(d3)) {//Aqui verifica se a hora de saida está depois da hora atual
            passe.setValor(0);
            System.out.println("Liberado → " + ns);
        } else {
            System.err.println("Excedeu Periodo de Tolerancia em: "+ns+" Minutos");
            
            for (int i = 0; i < bd.getBdTabPreco().size(); i++) {
                if (tempoDuracao >= bd.getBdTabPreco().get(i).getTempo()) {
                    passe.setValor(bd.getBdTabPreco().get(i).getMoeda());
                }
            }
            System.out.println("Cobrar: " + passe.getValor());
        }
         */
    
         
//Acaba auqi 
        String dateStr = "Fri Aug 24 19:19:22 BRT 2018";
        Locale loc = new Locale("pr", "BR");
        DateFormat readFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy",loc);
        DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = "";
        if (date != null) {
            formattedDate = writeFormat.format(date);
        }
        System.out.println(formattedDate);
    }
}
