package restControllers;

import java.text.SimpleDateFormat;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Meeting;
import entities.User;
import login.Secured;
import services.DAOUser;

@Path("/users")
public class UserRestController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		List<User> result = DAOUser.getInstance().getUsers();
		return result;
	}
	
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
	public Response createUser(User user) {
		User result= DAOUser.getInstance().createUser(user.getUserName(), user.getName(), user.getLastname(), user.getEmail(), user.getPassword());
			return Response.status(201).entity(result).build();
		
	}

	@DELETE
	@Secured
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id) {
		boolean result = DAOUser.getInstance().delete(id);
		if(result==false) {
			throw new RecursoNoExiste(id);
		}else {
			return Response.status(200).entity("El recurso con ID "+id+" ha "
					+ "sido eliminado").type(MediaType.TEXT_PLAIN).build();
		}		
	}

	@PUT
	@Secured
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, User user) {
		User result= DAOUser.getInstance().update(id, user.getUserName(), user.getName(), user.getLastname(), user.getEmail(), user.getPassword());
			return Response.status(201).entity(result).build();
	}

	@GET
	@Secured
	@Path("/getMeetingsByUserAndDay?userid={userid}&date={date}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getMeetingsByUserAndDay(@QueryParam("userid")int userid, @QueryParam("date")String dateString) {
		final Date date;
	    try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy,HH,mm");
	        date = formatter.parse(dateString); 
	    } catch(Exception e) {
	        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
	        		.entity("El formate de fecha no es correcto").type(MediaType.TEXT_PLAIN).build());
	    }
		return DAOUser.getInstance().getMeetingsByUserAndDay(userid, date);
	}
	
	@GET
	@Secured
	@Path("/getMeetingsByUserBetweenDates?userid={userid}&date1={date1}&date2={date2}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getMeetingsByUserBetweenDates(@QueryParam("userid")int userid, @QueryParam("date")String dateString1, @QueryParam("date")String dateString2) {
		final Date date1;
		final Date date2;
	    try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy,HH,mm");
	        date1 = formatter.parse(dateString1); 
	        date2= formatter.parse(dateString2);
	    } catch(Exception e) {
	        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
	        		.entity("El formate de fecha no es correcto").type(MediaType.TEXT_PLAIN).build());
	    }
		return DAOUser.getInstance().getMeetingsByUserBetweenDates(userid, date1, date2);
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
