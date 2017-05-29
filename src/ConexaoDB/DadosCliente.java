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

    public void cadastrar(Cliente cliente) throws Exception {

        String sql = "INSERT INTO cliente (cpf, nome, logradouro, numero, bairro, cidade, estado, cep, email, telefone)";
        sql += " VALUES (?,?,?,?,?,?,?,?,?,?)";
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

    public ArrayList<Cliente> listar(Cliente filtro) throws Exception {

        ArrayList<Cliente> listaclientes = new ArrayList<>();
        String sql = "SELECT cpf, nome, logradouro, numero, bairro, cidade, estado, cep, email, telefone FROM cliente WHERE cpf is not null";
        if (filtro.getNome() != null && filtro.getNome().equals("") == false) {
            sql += " and nome like '%"+ filtro.getNome()+"%'";
        }
        PreparedStatement cmd = this.conectarComParametros(sql);
        //executando a instrução sql
        ResultSet leitor = cmd.executeQuery(sql);
        while (leitor.next()) {
            Cliente c = new Cliente();
            c.setNome(leitor.getString("nome"));
            c.setCpf(leitor.getString("cpf"));
            c.setLogradouro(leitor.getString("logradouro"));
            c.setNumero(leitor.getInt("numero"));
            c.setBairro(leitor.getString("bairro"));
            c.setCidade(leitor.getString("cidade"));
            c.setEstado(leitor.getString("estado"));
            c.setCep(leitor.getInt("cep"));
            c.setEmail(leitor.getString("email"));
            c.setTelefone(leitor.getInt("telefone"));
            listaclientes.add(c);
        }
        return listaclientes;

    }

}
