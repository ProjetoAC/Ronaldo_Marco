
package br.com.senai.aprendercrescer.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/message")
public class MessageRestService {

    @GET
    @Path("/ret/{batatinha}")
    public Response printMessage(@PathParam("batatinha") String mensagem) {
        String resultado = "123 testando a vida do " + mensagem;
        return Response.status(200).entity(resultado).build();
    }

    @GET
    @Path("/ping")
    @Produces(value = "application/json")
    public String ping() {
        return "{'ping':'pong'}";
    }

}
