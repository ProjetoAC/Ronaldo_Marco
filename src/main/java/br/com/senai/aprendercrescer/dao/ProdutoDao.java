
package br.com.senai.aprendercrescer.dao;


import br.com.senai.aprendercrescer.model.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoDao {

    Statement st;

    public ProdutoDao() {
        try {
            st = Conexao.getConexao().createStatement();
        } catch (SQLException ex) {
            System.out.println("Erro ao pegar conexao" + ex);
        }
    }
        
    public ArrayList<Produto> getProduto() {
        ResultSet rs;
        Produto produto;
        ArrayList<Produto> lista = new ArrayList<Produto>();
        try {
            rs = st.executeQuery("SELECT  IDPRODUTO, PRECONORMAL,TIPOPRODUTO,FLAGINATIVO,DTALTERACAO,DTCADASTRO"
              + "FROM VENDA ORDER BY  IDVENDA ASC");
                    
            while (rs.next()) {
                produto = new Produto();
                produto.setIdProduto(rs.getInt("IDPRODUTO"));
                produto.setPrecoNormal(rs.getString("PRECONORMAL"));
                produto.setTipoProduto(rs.getString("TIPOPRODUTO"));
                produto.setFlagInativo(rs.getString("FLAGINATIVO"));
                produto.setDtAlteracao(rs.getString("DTALTERACAO"));
                produto.setDataCadastro(rs.getString("DTCADASTRO"));
               
            }
        } catch (SQLException ex) {
            System.out.println("Erro de consulta" + ex);
        }
        return lista;
    }
}


