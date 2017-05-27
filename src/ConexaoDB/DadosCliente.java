/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoDB;

import classesbasicas.Cliente;
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natália
 */
public class DadosCliente extends Conexao {
    
    public void cadastrar(Cliente cliente) throws Exception{
         
         String sql = "INSERT INTO cliente (cpf, nome, logradouro, numero, bairro, cidade, estado, cep, email, telefone)";
         sql+= " VALUES (?,?,?,?,?,?,?,?,?,?)";
         PreparedStatement pst = this.conectarComParametros(sql);
         pst.setString(1, cliente.getCpf());
         pst.setString(2, cliente.getNome());
         pst.setString(3, cliente.getLogradouro());
         pst.setInt(4, cliente.getNumero());
         pst.setString(5, cliente.getBairro());
         pst.setString(6, cliente.getCidade());
         pst.setString(7, cliente.getEstado());
         pst.setInt(8, cliente.getCep());
         pst.setString(9, cliente.getEmail());
         pst.setInt(10, cliente.getTelefone());
         
         //executando a instrução
         pst.executeUpdate();
         
         desconectar();
    }
    
 
     
     public ArrayList<Cliente> listar() throws Exception {

        ArrayList<Cliente> retorno = new ArrayList<>();
        try {
            Statement conex = conectar();
            String sql = "SELECT cpf, nome, logradouro, numero, bairro, cidade, estado, cep, email, telefone FROM cliente";

            //executando a instrução sql
            ResultSet rs = conex.executeQuery(sql);
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setLogradouro(rs.getString("logradouro"));
                c.setNumero(rs.getInt("numero"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                c.setCep(rs.getInt("cep"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getInt("telefone"));
                
                retorno.add(c);
            }
            //fechando a conexão com o banco de dados
            desconectar();

        } catch (SQLException e) {
            //caso haja algum erro nesse método será levantada esta exceção
            throw new Exception("Erro ao executar a seleção de clientes: " + e.getMessage());
        }
        //fechando a conexão com o banco de dados
        desconectar();
        return retorno;
     }
   
}
