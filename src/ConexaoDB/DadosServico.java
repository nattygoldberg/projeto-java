/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoDB;

import classesbasicas.Servico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Natália
 */
public class DadosServico extends Conexao {
    
    public void cadastrar(Servico servico) throws Exception {

        String sql = "INSERT INTO servico (descricao, valor)";
        sql += " VALUES (?,?)";
        PreparedStatement pst = this.conectarComParametros(sql);
        pst.setString(1, servico.getDescricao());
        pst.setFloat(2, servico.getValor());
        
        //executando a instrução
        pst.executeUpdate();

        desconectar();
    }
    
    public ArrayList<Servico> listar(Servico filtro) throws Exception {

        ArrayList<Servico> listaservicos = new ArrayList<>();
        String sql = "SELECT descricao, valor FROM servico WHERE id is not null";
        if (filtro.getDescricao()!= null && filtro.getDescricao().equals("") == false) {
            sql += " and descricao like '%"+ filtro.getDescricao()+"%'";
        }
        PreparedStatement cmd = this.conectarComParametros(sql);
        //executando a instrução sql
        ResultSet leitor = cmd.executeQuery(sql);
        while (leitor.next()) {
            Servico s = new Servico();
            s.setDescricao(leitor.getString("descricao"));
            s.setValor(leitor.getFloat("valor"));
            listaservicos.add(s);
        }
        return listaservicos;

    }
    public void editar(Servico servico) throws Exception{
                
        Statement conn = conectar();
        String sql = "UPDATE servico SET descricao=?, valor=?"
                + "WHERE id=?" ;
        PreparedStatement pst = this.conectarComParametros(sql);
        pst.setString(1, servico.getDescricao());
        pst.setFloat(2, servico.getValor());
        pst.setInt(3, servico.getId());

        //executando a instrução
        pst.executeUpdate();

        desconectar();
                
    }
    public  void deletar(Servico servico) throws Exception{
        Statement conn = conectar();
        String sql = "DELETE FROM servico WHERE id=?";
        PreparedStatement pst = this.conectarComParametros(sql);
        pst.setInt(1, servico.getId());
        //executando a instrução
        pst.executeUpdate();
        desconectar();
        
    }
    
}
