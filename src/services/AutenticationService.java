package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.User;

@Path("/autentication")
public class AutenticationService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarUser(Credential credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();


        if (usuarioValido(username, password)) {
            String token = TokenHelper.generarToken(username);
            return Response.ok(token).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    private boolean usuarioValido(String username, String password) {
        User resultado;
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        try {
            resultado = EMF.createEntityManager().find(User.class, username);
        }
        catch (Exception e){
            return false;
        }

        return resultado.equals(user);

    }

}
