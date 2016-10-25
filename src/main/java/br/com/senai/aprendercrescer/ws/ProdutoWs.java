
package br.com.senai.aprendercrescer.ws;

import br.com.senai.aprendercrescer.controller.ProdutoController;
import br.com.senai.aprendercrescer.model.Produto;
import br.com.senai.aprendercrescer.ws.VendaWs;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/produto")
public class ProdutoWs {

    @GET
    @Path("/getproduto")
    @Produces("application/json")
    public Response getProduto() {
        try {
            JSONObject retorno = new JSONObject();
            retorno.put("nome", "Marco ");
            retorno.put("idade", 22);
            return Response.status(200).entity(retorno.toString()).build();
        } catch (JSONException ex) {
            Logger.getLogger(VendaWs.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.status(500).build();
    }

    @GET
    @Path("/getvenda")
    @Produces("application/json")
    public Response getAllProduto() {
        try {
            ProdutoController produtoControler;
            produtoControler = new ProdutoController();
            ArrayList<Produto> lista = produtoControler.getProduto();

            JSONObject jProduto;
            StringBuilder retorno = new StringBuilder();
            retorno.append("[");
            boolean controle = false;
            for (Produto produto : lista) {
                if (controle) {
                    retorno.append(" , ");
                }

                jProduto = new JSONObject();
                jProduto.put("IDPRODUTO", produto.getIdProduto());
                jProduto.put("PRECONORMAL", produto.getPrecoNormal());
                jProduto.put("TIPOPRODUTO",produto.getTipoProduto());
                jProduto.put("FLAGINATIVO", produto.getFlagInativo() + "");
                jProduto.put("DTALTERACAO",produto.getDtAlteracao());
                jProduto.put("DTCADASTRO",produto.getDataCadastro());
                
                retorno.append(jProduto.toString());
                controle = true;
            }

            retorno.append("]");
            System.out.println(retorno.toString());
            return Response.status(200).entity(retorno.toString()).build();
        } catch (Exception ex) {
            System.out.println("Erro:" + ex);
            return Response.status(200).entity(
                    "{erro : \"" + ex + "\"}").build();

        }
    }

    
}
    
    
    /*
    @POST
    @Path("/gettvenda")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response getProduto(InputStream dadosServ) {
        StringBuilder requisicaoFinal = new StringBuilder();
        try {
            BufferedReader in
                    = new BufferedReader(
                            new InputStreamReader(dadosServ));
            String requisicao = null;
            while ((requisicao = in.readLine()) != null) {
                requisicaoFinal.append(requisicao);
            }
            System.out.println(requisicaoFinal.toString());

            JSONObject resposta
                    = new JSONObject(requisicaoFinal.toString());
            Produto produto = new Produto();

            produto.setIdProduto(resposta.getInt("IDPRODUTO"));
            produto.setPrecoNormal(resposta.getString("PRECONORMAL"));
            produto.setTipoProduto(resposta.getString("TIPOPRODUTO"));
            produto.setFlagInativo(resposta.getString("FLAGINATIVO"));
            produto.setDtAlteracao(resposta.getString("DTALTERACAO"));
            produto.setDataCadastro(resposta.getString("DTCADASTRO"));
           

            if (new ProdutoController().(produto)) {
                return Response.status(200).
                        entity("{\"result\""
                                + " : \"Cadastrado\"}").build();
            } else {
                return Response.status(501)
                        .entity("{\"result\" :"
                                + " \"Erro no Cadastro\"}").build();
            }
        } catch (Exception ex) {
            return Response.status(400).
                    entity(ex.toString()).build();
        }
    }
}*/