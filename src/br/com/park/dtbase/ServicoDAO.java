package br.com.park.dtbase;

import br.com.park.job.Servicos;
import java.awt.Choice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import oracle.jrockit.jfr.tools.ConCatRepository;

public class ServicoDAO {

    private static ArrayList<Servicos> bdServico = new ArrayList();

    public void salvaServico(Servicos serv) {
        bdServico.add(serv);
        try {
            create(serv);
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Servicos> getBdServico() {
        return bdServico;
    }

    public void setBdServico(ArrayList<Servicos> bdServico) {
        this.bdServico = bdServico;
    }

    public void popComboServ(JComboBox cb) {
        if (getBdServico().isEmpty()) {
            cb.addItem("Não possui");
        } else {
            try {
                for (Servicos servico : getBdServico()) {
                    cb.addItem(servico.getNome());
                }
            } catch (Exception ex) {
                Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void create(Servicos servico) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        String sql = "INSERT INTO servico(id_servico,nome_servico,acesso_servico,tolerancia,unidadeDeMedida)VALUES(?,?,?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setInt(1, servico.getId());
            stmt.setString(2, servico.getNome());
            stmt.setInt(3, servico.getAcessos());
            stmt.setInt(4, servico.getTolerancia());
            stmt.setString(5, servico.getUnidadeMedida());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TBdao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não salvou!" + ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void teste() {
        for (Servicos servicos : bdServico) {
            System.err.println(servicos.getNome());
        }
    }

    public List<Servicos> writeService() throws SQLException{
        bdServico.clear();
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = conexao.prepareStatement("select * from servico");
        ResultSet rs = stmt.executeQuery();
        try {
            while (rs.next()) {
                Servicos s = new Servicos();
                s.setId(rs.getInt("ID"));
                s.setNome(rs.getString("nome_servico"));
                s.setAcessos(rs.getInt("acesso_servico"));
                s.setTolerancia(rs.getInt("tolerancia"));
                s.setUnidadeMedida("unidadeDeMedida");
                bdServico.add(s);
            }
        } catch (Exception e) {
            System.err.println("ERROR "+e);
        }finally{
            ConnectionFactory.closeConnection(conexao, stmt, rs);
        }
        return bdServico;
    }

}
