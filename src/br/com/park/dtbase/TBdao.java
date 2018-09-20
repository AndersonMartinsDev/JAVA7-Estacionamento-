package br.com.park.dtbase;

import br.com.park.job.TabelaPreco;
import br.com.park.job.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.CullFace;
import javax.swing.JOptionPane;

public class TBdao {

    private static ArrayList<TabelaPreco> tb = new ArrayList();

    public ArrayList<TabelaPreco> getTb() {
        return tb;
    }

    public void create(TabelaPreco tb) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        String sql = "INSERT INTO tabelapreco (tempo,moeda)VALUES(?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setDouble(1, tb.getTempo());
            stmt.setFloat(2, tb.getMoeda());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(TBdao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não salvou!" + ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public List<TabelaPreco> bdtoArray() throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = conexao.prepareStatement("select * from tabelapreco");
        ResultSet rs = stmt.executeQuery();
        try {
            while (rs.next()) {
                TabelaPreco t = new TabelaPreco();
                t.setId(rs.getInt("ID"));
                t.setTempo(rs.getDouble("tempo"));
                t.setMoeda(rs.getFloat("moeda"));

                tb.add(t);
            }
        } catch (Exception e) {
            System.err.println("POP ARRAY" + e);
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
        return tb;
    }

}
