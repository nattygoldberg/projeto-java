/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoDB;

import classesbasicas.Servico;
import java.sql.PreparedStatement;


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
    
   
    
}
