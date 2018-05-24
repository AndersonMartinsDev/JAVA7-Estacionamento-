
package br.com.park.dtbase;

import br.com.park.client.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FuncionarioDAO {
    
    public void save(Funcionario func) throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        String sql = "INSERT INTO funcionario(nome_func,telefone_func,email_func,login,senha)VALUES(?,?,?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getTelefone());
            stmt.setString(3, func.getEmail());
            stmt.setString(4, func.getLogin());
            stmt.setString(5, func.getSenha());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionário Salvo!");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Algo Deu errado!");
        }finally{
           ConnectionFactory.closeConnection(conexao, stmt);
        }
        
    }
}
