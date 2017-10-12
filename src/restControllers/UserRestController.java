package restControllers;

import java.util.Date;
import java.util.List;

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

import entities.Meeting;
import entities.User;
import services.DAOUser;

@Path("/users")
public class UserRestController {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		User user = DAOUser.getInstance().getUser(id);
		if(user!=null)
			return user;
		else
			throw new RecursoNoExiste(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(String name, String lastName,String email) {
		User result= DAOUser.getInstance().createUser(name, lastName, email);
			return Response.status(201).entity(result).build();
		
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePerro(@PathParam("id") int id) {
		boolean result = DAOUser.getInstance().delete(id);
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
	public Response updateUser(@PathParam("id") int id,String name, String lastName,String email) {
		User result= DAOUser.getInstance().update(id,name, lastName,email);
			return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/getMeetingsByUserAndDay")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getMeetingsByUserAndDay(User user, Date date) {
		return DAOUser.getInstance().getMeetingsByUserAndDay(user, date);
	}
	
	@GET
	@Path("/getMeetingsByUserBetweenDates")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getMeetingsByUserBetweenDates(User user, Date date1, Date date2) {
		return DAOUser.getInstance().getMeetingsByUserBetweenDates(user, date1, date2 );
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
