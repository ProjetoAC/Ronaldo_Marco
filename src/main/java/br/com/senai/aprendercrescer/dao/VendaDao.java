package br.com.senai.aprendercrescer.dao;

import br.com.senai.aprendercrescer.dao.Conexao;
import br.com.senai.aprendercrescer.model.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendaDao {

    Statement st;

    public VendaDao() {
        try {
            st = Conexao.getConexao().createStatement();
        } catch (SQLException ex) {
            System.out.println("Erro ao pegar conexao" + ex);
        }
    }

    public ArrayList<Venda> getVenda() {
        ResultSet rs;
        Venda venda;
        ArrayList<Venda> lista = new ArrayList<Venda>();
        try {
            rs = st.executeQuery("SELECT  IDVENDA, IDPRODUTO,VALORVENDALIQ,DTMOVIMENTO"
                    + "FROM VENDA ORDER BY  IDVENDA ASC");

            while (rs.next()) {
                venda = new Venda();
                venda.setIdVenda(rs.getInt("IDVENDA"));
                venda.setIDproduto(rs.getInt("IDPRODUTO"));
                venda.setValorVendaliq(rs.getDouble("VALORVENDALIQ"));
                venda.setDtMovimento(rs.getString("DTMOVIMENTO"));

            }
        } catch (SQLException ex) {
            System.out.println("Erro de consulta" + ex);
        }
        return lista;
    }

    public boolean insereVenda(Venda venda) {
        String sql = "";
        ResultSet rs;
        int id = 0;
        try {

            sql = "SELECT COALESCE(MAX(idvenda)+1, 1) AS idvenda FROM venda ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("idvenda");
            }

            venda.setIdVenda(id);

            sql = "INSERT INTO venda("
                    + "            idvenda, "/*flagcancelada, idcliente, */ + "idproduto, valorvendaliq) VALUES ("
                    + venda.getIdVenda()
                    + venda.getIDproduto()
                    + venda.getValorVendaliq() + ")";
            
            
            
            System.out.println(sql);
            st.execute(sql);

            return true;
        } catch (SQLException ex) {
            System.out.println("Problema ao inserir usuario: " + ex);
        }
        return false;
    }
/*
    public boolean updateUsuario(Usuario usuario) {
//        Date data = new Date();
        String sql = "UPDATE usuario SET "
                + "idusuario=" + usuario.getIdUsuario() + ", "
                + "idgrupo= " + usuario.getIdGrupo() + " , "
                + "login='" + usuario.getLogin() + "',"
                + "senhausuario='" + usuario.getSenha() + "', "
                + "nomeusuario='" + usuario.getNome() + "',"
                + "dtalteracao='" + data + "', "
                + "flaginativo='" + usuario.getFlagInativo() + "' "
                + "WHERE idusuario= " + usuario.getIdUsuario() + ";";
        try {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro no Update :" + ex);
        }
        return false;
    }
*/
}
