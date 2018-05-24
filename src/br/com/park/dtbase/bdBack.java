package br.com.park.dtbase;

import br.com.park.client.Cliente;
import br.com.park.job.Caixa;
import br.com.park.job.Estacionamento;
import br.com.park.job.Servicos;
import br.com.park.job.TabelaPreco;
import br.com.park.job.Ticket;
import br.com.park.logs.Logs;
import java.awt.Choice;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class bdBack {
    TBdao bancoTi;
    private static ArrayList<Ticket> bdTicket = new ArrayList();
    private static ArrayList<Estacionamento> bdEstacionamento = new ArrayList();
    
    private static ArrayList<Caixa> bdConvenio = new ArrayList();
    private static ArrayList<TabelaPreco> bdTabPreco = new ArrayList();
    private static ArrayList<Logs> bdlog = new ArrayList();
    private static ArrayList<Cliente> bdCliente = new ArrayList<>();

    //  <---------------------------    >>>>>>>>
    public void salvaCliente(Cliente cliente) {
        bdCliente.add(cliente);
        bdTicket.add(cliente.getPasse());
    }

    public void salvaEstacionamento(Estacionamento Estacionamento) {
        bdEstacionamento.add(Estacionamento);
    }

    

    public void salvaConvenio(Caixa convenio) {
        bdConvenio.add(convenio);
    }

    public void salvaTbPreco(TabelaPreco TabelaPreco) {
        bdTabPreco.add(TabelaPreco);
    }

    public void salvaTicket(Ticket ticket) {
         bancoTi = new TBdao();
        bdTicket.add(ticket);
       /* try {
            bancoTi.create(ticket);
        } catch (SQLException ex) {
            Logger.getLogger(bdBack.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

   

    public void salvaLog(Logs log) {
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

    public static ArrayList<Cliente> getBdCliente() {
        return bdCliente;
    }

    public static void setBdCliente(ArrayList<Cliente> bdCliente) {
        bdBack.bdCliente = bdCliente;
    }

    //---------------------------------------------------------
   

    public void popCombConvenio(JComboBox cb) {
        cb.removeAll();
        if (getBdConvenio().isEmpty()) {
            cb.addItem("Não possui");
        } else {
            for (Caixa convenio : bdConvenio) {
                cb.addItem(convenio.getConvenioNome());
            }
        }
    }
}
