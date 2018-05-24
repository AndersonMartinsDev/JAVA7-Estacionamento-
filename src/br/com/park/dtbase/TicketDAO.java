package br.com.park.dtbase;

import br.com.park.job.Ticket;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TicketDAO {
    private static ArrayList <Ticket> ticketArray = new ArrayList();

    public  ArrayList<Ticket> getTicketArray() {
        return ticketArray;
    }
    
    public List<Ticket>queryFilter(String busca)throws SQLException{
        ticketArray.clear();
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = conexao.prepareStatement("select * from ticket "+busca);
        ResultSet rs = stmt.executeQuery();
       
        try {
            while(rs.next()){
                Ticket t = new Ticket();
                t.setId(rs.getInt("ID"));
                t.setCodigo(rs.getInt("codigo"));
                t.setStatus(rs.getString("status"));
                t.setEntrada(rs.getTimestamp("dataEntrada"));
                t.setSaida(rs.getTimestamp("dataSaida"));
                t.setValor(rs.getFloat("valor"));
                t.setServ(rs.getString("servico"));
               // t.setCx(rs.getInt("id_caixa"));
               ticketArray.add(t);
            }
        } catch (Exception e) {
            System.err.println("POP ARRAY" + e);
        }finally{
            ConnectionFactory.closeConnection(conexao, stmt,rs);
        }
        return ticketArray;
    }
  
    public void create(Ticket ticket) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        String sql = "INSERT INTO ticket (id_ticket,codigo,status,dataEntrada,"
                + "dataSaida,valor,servico,id_caixa)VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        java.sql.Timestamp dataEnt = new java.sql.Timestamp(ticket.getEntrada().getTime());
        java.sql.Timestamp dataSai = new java.sql.Timestamp(ticket.getSaida().getTime());
        
        System.err.println("Data banco " + dataEnt);
        System.err.println("Data banco " + dataSai);
        try {
            stmt.setInt(1, ticket.getId());
            stmt.setDouble(2, ticket.getCodigo());
            stmt.setString(3, ticket.isStatus());
            stmt.setTimestamp(4, dataEnt);
            stmt.setTimestamp(5, dataSai);
            stmt.setFloat(6, ticket.getValor());
            stmt.setString(7, ticket.getServ());
            stmt.setInt(8, 0);// Mudar aqui para receber o caixa por conta dos desconto
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não salvou!" + ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }
    public List<Ticket> bdtoArray()throws SQLException{
        ticketArray.clear();
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = conexao.prepareStatement("select * from ticket");
        ResultSet rs = stmt.executeQuery();
       
        try {
            while(rs.next()){
                Ticket t = new Ticket();
                t.setId(rs.getInt("ID"));
                t.setCodigo(rs.getInt("codigo"));
                t.setStatus(rs.getString("status"));
                t.setEntrada(rs.getTimestamp("dataEntrada"));
                t.setSaida(rs.getTimestamp("dataSaida"));
                t.setValor(rs.getFloat("valor"));
               //t.setServ(rs.getInt("id_servico"));
               // t.setCx(rs.getInt("id_caixa"));
               ticketArray.add(t);
            }
        } catch (Exception e) {
            System.err.println("POP ARRAY" + e);
        }finally{
            ConnectionFactory.closeConnection(conexao, stmt,rs);
        }
        return ticketArray;
    }
    
       public Ticket consultaTicket(double ticket) {
        Ticket passe = null;
        double numero;
        for (int i = 0; i < ticketArray.size(); i++) {
            numero =(int)ticketArray.get(i).getCodigo();
            if (ticket == numero) {
                passe = ticketArray.get(i);
            }
        }
        return passe;
        
    }
        
}
