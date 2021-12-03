package ConnectionFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Heloisa Oliveira Timóteo
 */
public class ConexaoMySQL {
    public static String status;
    
    public static Connection getConexaoMySQL(){
        Connection conexao;
        
        try{
            String url = "jdbc:mysql://localhost/Agenda";
            String usuario = "root";
            String senha = "root";
            
            conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
            if (conexao != null)
                status = "Conectado com sucesso!";
            else status = "Não foi possível conectar";
            System.out.println(status);
            return conexao;
        }
        catch(SQLException e){
            System.out.println("Não conectou ao bando de dados");
            return null;
        }
    }
    public static String statusConnection(){
        return status;
    }
    
    public static boolean FecharConexao(){
        try {
            ConexaoMySQL.getConexaoMySQL().close();
            return true;
        }
        catch(SQLException e){
            return false;
        }
    }
    
    public static Connection ReiniciarConexao(){
        FecharConexao();
        return ConexaoMySQL.getConexaoMySQL();
    }
}
