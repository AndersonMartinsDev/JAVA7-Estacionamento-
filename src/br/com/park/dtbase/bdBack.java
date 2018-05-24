package br.com.park.dtbase;

import br.com.park.job.Caixa;
import br.com.park.job.Estacionamento;
import br.com.park.job.Servicos;
import br.com.park.job.TabelaPreco;
import br.com.park.job.Ticket;
import br.com.park.logs.Logs;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class bdBack {
   
    private static ArrayList<Ticket> bdTicket = new ArrayList();
    private static ArrayList<Estacionamento> bdEstacionamento = new ArrayList();
    private static ArrayList<Servicos> bdServico = new ArrayList();
    private static ArrayList<Caixa> bdConvenio = new ArrayList();
    private static ArrayList<TabelaPreco> bdTabPreco = new ArrayList();
    private static ArrayList<Logs> bdlog = new ArrayList();

  //  <---------------------------    >>>>>>>>
     public void salvaEstacionamento(Estacionamento Estacionamento) {
        bdEstacionamento.add(Estacionamento);
    }

    public void salvaServico(Servicos serv) {
       bdServico.add(serv);     
       System.out.println("ArrayServicos "+ bdServico.size());
 
    }

    public void salvaConvenio(Caixa convenio) {
        bdConvenio.add(convenio);
    }

    public void salvaTbPreco(TabelaPreco TabelaPreco) {
        bdTabPreco.add(TabelaPreco);
        
        System.out.println("Array TB preço: "+bdTabPreco.size());
    }

    public void salvaTicket(Ticket ticket) {
        bdTicket.add(ticket);
        System.out.println("Ticket Salvo " + bdTicket.size()+"\n"+ticket.getId());
    }

    public String consultaTicket(double ticket) {
        int passe = 0;
        double numero;
       
        for (int i = 0; i < bdTicket.size(); i++) {
            numero = bdTicket.get(i).getCodigo();
            if (ticket == numero) {
                passe = bdTicket.get(i).getId();
            }
        }

      
        return bdTicket.get(passe).toString();
        
    }
    public void salvaLog(Logs log){
        bdlog.add(log);
    }
 
    public static ArrayList<Logs> getBdlog() {
        return bdlog;
    }

    public static void setBdlog(ArrayList<Logs> bdlog) {
        bdBack.bdlog = bdlog;
    }
    public ArrayList<Ticket> getBdTicket() {
        return bdTicket;
    }

    public void setBdTicket(ArrayList<Ticket> bdTicket) {
        this.bdTicket = bdTicket;
    }

    public ArrayList<Estacionamento> getBdEstacionamento() {
        return bdEstacionamento;
    }

    public void setBdEstacionamento(ArrayList<Estacionamento> bdEstacionamento) {
        this.bdEstacionamento = bdEstacionamento;
    }

    public ArrayList<Servicos> getBdServico() {
        return bdServico;
    }

    public void setBdServico(ArrayList<Servicos> bdServico) {
        this.bdServico = bdServico;
    }

    public ArrayList<Caixa> getBdConvenio() {
        return bdConvenio;
    }

    public void setBdConvenio(ArrayList<Caixa> bdConvenio) {
        this.bdConvenio = bdConvenio;
    }

    public ArrayList<TabelaPreco> getBdTabPreco() {
        return bdTabPreco;
    }

    public void setBdTabPreco(ArrayList<TabelaPreco> bdTabPreco) {
        this.bdTabPreco = bdTabPreco;
    }
}
