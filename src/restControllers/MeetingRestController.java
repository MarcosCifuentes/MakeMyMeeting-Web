package restControllers;

import java.util.Date;
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
import entities.Calendar;
import entities.Meeting;
import entities.Site;
import entities.User;
import services.DAOMeeting;



@Path("/meetings")
public class MeetingRestController {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Meeting getMeetingById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Meeting meeting = DAOMeeting.getInstance().getMeeting(id);
		if(meeting!=null)
			return meeting;
		else
			throw new RecursoNoExiste(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMeeting(String name, Date dateStart, Date dateEnd, Site site, Calendar calendar, User user) {
		Meeting result= DAOMeeting.getInstance().createMeeting(name, dateStart, dateEnd, site, calendar, user);
		return Response.status(201).entity(result).build();

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMeeting(@PathParam("id") int id) {
		boolean result = DAOMeeting.getInstance().delete(id);
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
	public Response updateMeeting(@PathParam("id") int id, String name, Date dateStart, Date dateEnd, Site site, Calendar calendar, User user) {
		Meeting result= DAOMeeting.getInstance().update(id,name, dateStart, dateEnd, site, calendar, user);
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

