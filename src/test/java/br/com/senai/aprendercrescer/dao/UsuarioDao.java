/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.aprendercrescer.dao;

import br.com.senai.aprendercrescer.model.Usuario;
import com.sun.org.apache.regexp.internal.REUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Caluan Baierle
 */
public class UsuarioDao {

    Statement st;

    public UsuarioDao() {
        try {
            st = Conexao.getConexao().createStatement();
        } catch (SQLException ex) {
            System.out.println("Erro ao pegar conexao" + ex);
        }
    }

    public Usuario getUsuarioByID(int id) {
        ResultSet rs;
        Usuario usuario;
        try {
            rs = st.executeQuery("SELECT  IDUSUARIO, IDGRUPO,LOGIN,"
                    + " SENHAUSUARIO, NOMEUSUARIO,DTALTERACAO,"
                    + "FLAGINATIVO FROM USUARIO WHERE IDUSUARIO = " + id);
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
                usuario.setLogin(rs.getString("LOGIN"));
                usuario.setNome(rs.getString("NOMEUSUARIO"));
                usuario.setSenha(rs.getString("SENHAUSUARIO"));
                return usuario;
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public boolean insereUsuario(Usuario usuario) {
        String sql = "";
        Date data = new Date();
        ResultSet rs;
        int id = 0;
        try {

            sql = "SELECT COALESCE(MAX(IDUSUARIO)+1, 1) AS IDUSUARIO FROM USUARIO ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("IDUSUARIO");
            }
            
            usuario.setIdUsuario(id);
            sql = "INSERT INTO usuario( idusuario, idgrupo, login, "
                    + "senhausuario, nomeusuario, dtalteracao, flaginativo)"
                    + "VALUES (" + usuario.getIdUsuario()
                    + ", " + usuario.getIdGrupo()
                    + ", '" + usuario.getLogin()
                    + "' , '" + usuario.getSenha()
                    + "' , '" + usuario.getNome()
                    + "' , '" + data.toString()
                    + "', 'F' )";
            System.out.println(sql);
            st.execute(sql);
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Problema ao inserir usuario: " + ex);
        }
        return false;
    }

    public boolean updateUsuario(Usuario usuario) {
        Date data = new Date();
        String sql = "UPDATE usuario SET "
                + "idusuario=" + usuario.getIdUsuario() + ", "
                + "idgrupo= "+usuario.getIdGrupo()+" , "
                + "login='" + usuario.getLogin() + "',"
                + "senhausuario='" + usuario.getSenha() + "', "
                + "nomeusuario='" + usuario.getNome() + "',"
                + "dtalteracao='" + data + "', "
                + "flaginativo='"+usuario.getFlagInativo()+"' "
                + "WHERE idusuario= " + usuario.getIdUsuario() + ";";
        try {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro no Update :" + ex);
        }
        return false;
    }

    public ArrayList<Usuario> getUsuarios() {
        ResultSet rs;
        Usuario usuario;
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            rs = st.executeQuery("SELECT  IDUSUARIO, IDGRUPO,LOGIN,"
                    + " SENHAUSUARIO, NOMEUSUARIO,DTALTERACAO,"
                    + "FLAGINATIVO FROM USUARIO ORDER BY  IDUSUARIO ");
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
                usuario.setLogin(rs.getString("LOGIN"));
                usuario.setNome(rs.getString("NOMEUSUARIO"));
                usuario.setSenha(rs.getString("SENHAUSUARIO"));
                usuario.setFlagInativo(rs.getString("FLAGINATIVO").toCharArray()[0]);
                usuario.setIdGrupo(rs.getInt("IDGRUPO"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de consulta" + ex);
        }
        return lista;
    }

    public boolean deleteUsuario(int id) {
        String sql = "DELETE FROM USUARIO WHERE IDUSUARIO = " + id;
        try {
            System.out.println("DElete usuario = "+id);
            st.execute(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro Delete: " + ex);
        }
        return false;
    }

    public Usuario validaLogin(Usuario usuario) {
        try {
            String sql = "SELECT  IDUSUARIO, "
                    + "IDGRUPO,LOGIN, SENHAUSUARIO,"
                    + " NOMEUSUARIO,DTALTERACAO,"
                    + " FLAGINATIVO FROM "
                    + "USUARIO WHERE "
                    + "LOGIN = '" + usuario.getLogin() + "' "
                    + "AND SENHAUSUARIO = '" + usuario.getSenha() + "'";
            ResultSet rs;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
                usuario.setLogin(rs.getString("LOGIN"));
                usuario.setNome(rs.getString("NOMEUSUARIO"));
                usuario.setSenha(rs.getString("SENHAUSUARIO"));
                return usuario;
            }

            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

}
