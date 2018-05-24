package br.com.park.dtbase;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CtrlGeral {

    TicketDAO loadTicket = new TicketDAO();
    ServicoDAO loadService = new ServicoDAO();

    public void loadBd() {
        try {
            
            loadService.writeService();
            loadTicket.bdtoArray();
        } catch (Exception e) {
            System.err.println("Não deu " + e);
        }
    }
}
