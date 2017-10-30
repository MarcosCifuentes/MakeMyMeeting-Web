package restControllers;

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
import entities.Calendar;
import login.Secured;
import services.CalendarRest;
import services.DAOCalendar;

@Path("/calendars")
public class CalendarRestController {

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public List<Calendar> getCalendars() {
		List<Calendar> result = DAOCalendar.getInstance().getCalendars();
		return result;
	}

	@GET
	@Secured
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Calendar getCalendarById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Calendar calendar = DAOCalendar.getInstance().getCalendar(id);
		if(calendar!=null)
			return calendar;
		else
			throw new RecursoNoExiste(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCalendar(CalendarRest calendar) {
		Calendar result= DAOCalendar.getInstance().createCalendar(calendar.getName(), calendar.getIdUser());
		return Response.status(201).entity(result).build();

	}

	@DELETE
	@Secured
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCalendar(@PathParam("id") int id) {
		boolean result = DAOCalendar.getInstance().delete(id);
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
	public Response updateCalendar(@PathParam("id") int id, Calendar calendar) {
		Calendar result= DAOCalendar.getInstance().update(id, calendar.getName());
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

