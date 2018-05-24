
package br.com.park.dtbase;

import br.com.park.client.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    
    public void save(Cliente cliente)throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        String sql = "INSERT INTO cliente(nome_cliente, cpf_cliente, telefone_cliente, email_cliente)VALUES(?,?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve Algum problema \nCliente não foi salvo");
        }finally{
            ConnectionFactory.closeConnection(conexao,stmt);
        }
    }
    
}
