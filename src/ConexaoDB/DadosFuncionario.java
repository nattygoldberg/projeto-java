/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoDB;

import classesbasicas.Cliente;
import classesbasicas.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Natália
 */
public class DadosFuncionario extends Conexao {
    
     public void cadastrar(Funcionario funcionario) throws Exception {

        String sql = "INSERT INTO funcionario (cpf, nome, logradouro, numero, bairro, cidade, estado, cep, email, telefone)";
        sql += " VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = this.conectarComParametros(sql);
        pst.setString(1, funcionario.getCpf());
        pst.setString(2, funcionario.getNome());
        pst.setString(3, funcionario.getLogradouro());
        pst.setInt(4, funcionario.getNumero());
        pst.setString(5, funcionario.getBairro());
        pst.setString(6, funcionario.getCidade());
        pst.setString(7, funcionario.getEstado());
        pst.setInt(8, funcionario.getCep());
        pst.setString(9, funcionario.getEmail());
        pst.setInt(10, funcionario.getTelefone());

        //executando a instrução
        pst.executeUpdate();

        desconectar();
    }

    public ArrayList<Funcionario> listar(Funcionario filtro) throws Exception {

        ArrayList<Funcionario> listafuncionarios = new ArrayList<>();
        String sql = "SELECT cpf, nome, logradouro, numero, bairro, cidade, estado, cep, email, telefone FROM funcionario WHERE cpf is not null";
        if (filtro.getNome() != null && filtro.getNome().equals("") == false) {
            sql += " and nome like '%"+ filtro.getNome()+"%'";
        }
        PreparedStatement cmd = this.conectarComParametros(sql);
        //executando a instrução sql
        ResultSet leitor = cmd.executeQuery(sql);
        while (leitor.next()) {
            Funcionario f = new Funcionario();
            f.setNome(leitor.getString("nome"));
            f.setCpf(leitor.getString("cpf"));
            f.setLogradouro(leitor.getString("logradouro"));
            f.setNumero(leitor.getInt("numero"));
            f.setBairro(leitor.getString("bairro"));
            f.setCidade(leitor.getString("cidade"));
            f.setEstado(leitor.getString("estado"));
            f.setCep(leitor.getInt("cep"));
            f.setEmail(leitor.getString("email"));
            f.setTelefone(leitor.getInt("telefone"));
            listafuncionarios.add(f);
        }
        return listafuncionarios;

    }
    public void editar(Funcionario funcionario) throws Exception{
                
        Statement conn = conectar();
        String sql = "UPDATE funcionario SET cpf=?, nome=?, logradouro=?, numero=?, bairro=?, cidade=?, estado=?, cep=?"
                + ", email=?, telefone=? WHERE cpf=?" ;
        PreparedStatement pst = this.conectarComParametros(sql);
        pst.setString(1, funcionario.getCpf());
        pst.setString(2, funcionario.getNome());
        pst.setString(3, funcionario.getLogradouro());
        pst.setInt(4, funcionario.getNumero());
        pst.setString(5, funcionario.getBairro());
        pst.setString(6, funcionario.getCidade());
        pst.setString(7, funcionario.getEstado());
        pst.setInt(8, funcionario.getCep());
        pst.setString(9, funcionario.getEmail());
        pst.setInt(10, funcionario.getTelefone());
         pst.setString(11, funcionario.getCpf());

        //executando a instrução
        pst.executeUpdate();

        desconectar();
                
    }
    public  void deletar(Funcionario funcionario) throws Exception{
        Statement conn = conectar();
        String sql = "DELETE FROM funcionario WHERE cpf=?";
        PreparedStatement pst = this.conectarComParametros(sql);
        pst.setString(1, funcionario.getCpf());
        //executando a instrução
        pst.executeUpdate();
        desconectar();
        
    }
}
