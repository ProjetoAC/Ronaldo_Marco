package br.com.senai.aprendercrescer.ws;

import br.com.senai.aprendercrescer.controller.VendaController;
import br.com.senai.aprendercrescer.model.Venda;
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

@Path("/venda")
public class VendaWs {

    @GET
    @Path("/getvenda")
    @Produces("application/json")
    public Response getVenda() {
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
    public Response getAllVenda() {
        try {
            VendaController vendaControler;
            vendaControler = new VendaController();
            ArrayList<Venda> lista = vendaControler.getVenda();

            JSONObject jVenda;
            StringBuilder retorno = new StringBuilder();
            retorno.append("[");
            boolean controle = false;
            for (Venda venda : lista) {
                if (controle) {
                    retorno.append(" , ");
                }

                jVenda = new JSONObject();
                jVenda.put("IDVENDA", venda.getIdVenda());
                jVenda.put("IDPRODUTO", venda.getIDproduto());
                jVenda.put("VALORVENDALIQ",venda.getValorVendaliq());
                jVenda.put("DTMOVIMENTO", venda.getDtMovimento() + "");
                retorno.append(jVenda.toString());
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

    @POST
    @Path("/gettusuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response getVenda(InputStream dadosServ) {
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
            Venda venda = new Venda();

            venda.setIdVenda(resposta.getInt("IDVENDA"));
            venda.setIDproduto(resposta.getInt("IDPRODUTO"));
            venda.setValorVendaliq(resposta.getDouble("VALORVENDALIQ"));
            venda.setDtMovimento(resposta.getString("DTMOVIMENTO"));
           

            if (new VendaController().insereVenda(venda)) {
                return Response.status(200).
                        entity("{\"result\""
                                + " : \"Venda\"}").build();
            } else {
                return Response.status(501)
                        .entity("{\"result\" :"
                                + " \"Erro na venda!\"}").build();
            }
        } catch (Exception ex) {
            return Response.status(400).
                    entity(ex.toString()).build();
        }
    }
}