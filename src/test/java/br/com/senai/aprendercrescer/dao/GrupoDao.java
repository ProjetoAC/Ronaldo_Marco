/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.aprendercrescer.dao;

import br.com.senai.aprendercrescer.model.Grupo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Caluan Baierle
 */
public class GrupoDao {

    Statement st;

    public GrupoDao() {
        try {
            st = Conexao.getConexao().createStatement();
        } catch (SQLException ex) {
            System.out.println("Erro Ao Criar conexao com Grupo" + ex);
        }
    }

    public boolean insereGrupo(Grupo grupo) {
        String sql = "INSERT INTO "
                + "grupo(idgrupo, tipousuario, descricaogrupo) "
                + "VALUES((SELECT COALESCE(MAX(IDGRUPO)+1,1)"
                + " FROM GRUPO) , "
                + "'" + grupo.getTipoUsuario()+ "',"
                + "'" + grupo.getDescricaoGrupo()+ "')";

        try {
            System.out.println(sql);
            st.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Grupo> getGrupos() {
        ResultSet rs;
        Grupo grupo;
        ArrayList<Grupo> lista = new ArrayList<Grupo>();
        try {
            rs = st.executeQuery("SELECT IDGRUPO, TIPOUSUARIO,"
                    + " DESCRICAOGRUPO FROM GRUPO ");
            while (rs.next()) {
                grupo = new Grupo();
                grupo.setIdGrupo(rs.getInt("IDGRUPO"));
                grupo.setDescricaoGrupo(rs.getString("DESCRICAOGRUPO"));
                grupo.setTipoUsuario(rs.getString("TIPOUSUARIO"));

                lista.add(grupo);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de consulta" + ex);
        }
        return lista;
    }

    public boolean deleteGrupo(int id) {
        String sql = "DELETE FROM GRUPO WHERE IDGRUPO = " + id;
        try {
            st.execute(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro Delete: " + ex);
        }
        return false;
    }

    public boolean updateGrupo(Grupo grupo) {

        String sql = "UPDATE GRUPO SET "
                + "idgrupo=" + grupo.getIdGrupo()
                + ",tipousuario= '" + grupo.getTipoUsuario()
                + "',descricaogrupo='" + grupo.getDescricaoGrupo()
                + "' WHERE IDGRUPO = " + grupo.getIdGrupo();

        try {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro no Update :" + ex);
        }
        return false;

    }

}
