package br.com.park.dtbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bdpark?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }catch (ClassNotFoundException e) {            
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao banco de dados."+e);
            return null;
        }
    }

    public static void closeConnection(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement stmt) {

        closeConnection(conexao);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void closeConnection(Connection conexao, PreparedStatement stmt, ResultSet rs) {

        closeConnection(conexao, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
