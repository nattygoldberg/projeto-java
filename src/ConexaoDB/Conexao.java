/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoDB;

import Formularios.FormCadastroCliente;
import Formularios.FormPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natália
 */
public class Conexao extends FormCadastroCliente {

    private Statement stmt;
    private Connection conn;

    public Statement conectar() throws ClassNotFoundException, SQLException {
        return this.conectarMySql();
    }
    
    public PreparedStatement conectarComParametros (String sql) throws ClassNotFoundException, SQLException {
        this.conectarMySql();
        return conn.prepareStatement(sql);
}

    public void desconectar() throws SQLException {
        conn.close();
    }

    private Statement conectarMySql() throws ClassNotFoundException, SQLException {
        try {

            String driver = "com.mysql.jdbc.Driver";
            String dataBaseName = "projetojava";
            String url = "jdbc:mysql://localhost:3306/";
            String usuario = "root";
            String senha = "";
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(url + dataBaseName, usuario, senha);
            stmt = conn.createStatement();
            return stmt;
        } catch (InstantiationException ex) {
            throw new SQLException(ex.getMessage());
        } catch (IllegalAccessException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

}
