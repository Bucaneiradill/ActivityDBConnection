/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionFactory.ConexaoMySQL;
import Model.Contato;
import java.sql.*;
import java.sql.PreparedStatement;
import ConnectionFactory.ConexaoMySQL;
import java.util.ArrayList;
import View.TelaPrincipal;
import javax.swing.JOptionPane;
/**
 *
 * @author aluno
 */
public class ContatoDAO {
    ArrayList<String> listaContatos = new ArrayList<String>();
    private Connection conexao;
 
    public ContatoDAO() {
        conexao = ConexaoMySQL.getConexaoMySQL();
    }
    
    public void Adiciona(Contato contato){
        String sql = "INSERT INTO Contato(nome, telefone, email, endereco) VALUES(?,?,?,?)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getEndereco());
            stmt.execute();
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public void Atualizar(Contato c){
        String sql;
        
        try{
            sql = "UPDATE Contato set nome = '" + c.getNome() +  "', telefone = '" + c.getTelefone() + "', email = '" + c.getEmail() + "', endereco = '" + c.getEndereco() + "' where id = " + c.getId();
            JOptionPane.showMessageDialog(null, sql);
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.execute();
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public void Exluir(int id){
        String sql;
        
        try{
            sql = "delete from Contato where id = " + id;
            PreparedStatement stmt = conexao.prepareStatement(sql);
           
            stmt.execute();
            JOptionPane.showMessageDialog(null,"Usuário Excluído");
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public ArrayList<Contato> ConsultarTodos(){
        ArrayList<Contato> contatos = new ArrayList();
        Connection conexao = ConexaoMySQL.getConexaoMySQL();
        
        PreparedStatement stmt;
        String sql = "select * from Contato";

        try {   
            stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Contato c = new Contato();
                c.setId(Integer.parseInt(rs.getString("id")));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                contatos.add(c);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            ConexaoMySQL.FecharConexao();
        }
        return contatos;
    }
    
    public Contato consultar(int id){
        TelaPrincipal tela = new TelaPrincipal();
        Contato c = new Contato();
        PreparedStatement stmt;
        
        try {   
            String sql = "select * from Contato where id = " + id;
            JOptionPane.showMessageDialog(null, sql);
            stmt = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                c.setId(Integer.parseInt(rs.getString("id")));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
            }
            return c;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        finally{
            ConexaoMySQL.FecharConexao();
        }
    }
}
