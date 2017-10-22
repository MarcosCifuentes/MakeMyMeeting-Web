package restControllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entities.Invitation;
import entities.Meeting;
import entities.User;
import services.DAOInvitation;

@Path("/invitations")
public class InvitationRestController {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Invitation getInvitationById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Invitation invitation = DAOInvitation.getInstance().getInvitation(id);
		if(invitation!=null)
			return invitation;
		else
			throw new RecursoNoExiste(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createInvitation(Invitation invitation) {
		Invitation result= DAOInvitation.getInstance().createInvitation(invitation.getMeeting(), invitation.getUser());
		return Response.status(201).entity(result).build();

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteInvitation(@PathParam("id") int id) {
		boolean result = DAOInvitation.getInstance().delete(id);
		if(result==false) {
			throw new RecursoNoExiste(id);
		}else {
			return Response.status(200).entity("El recurso con ID "+id+" ha "
					+ "sido eliminado").type(MediaType.TEXT_PLAIN).build();
		}		
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInvitation(@PathParam("id") int id, Invitation invitation) {
		Invitation result= DAOInvitation.getInstance().update(id, invitation.getMeeting(), invitation.getUser());
		return Response.status(201).entity(result).build();
	}

	public class RecursoDuplicado extends WebApplicationException {
		public RecursoDuplicado(int id) {
			super(Response.status(Response.Status.CONFLICT)
					.entity("El recurso con ID "+id+" ya existe").type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class RecursoNoExiste extends WebApplicationException {
		public RecursoNoExiste(int id) {
			super(Response.status(Response.Status.NOT_FOUND)
					.entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
		}
	}
}

