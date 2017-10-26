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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entities.Meeting;
import services.DAOMeeting;

@Path("/meetings")
public class MeetingRestController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getUsers() {
		List<Meeting> result = DAOMeeting.getInstance().getMeetings();
		return result;
	}

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
	public Response createMeeting(Meeting meeting) {
		Meeting result= DAOMeeting.getInstance().createMeeting(meeting.getName(), meeting.getDateStart(), meeting.getDateEnd(), meeting.getSite(), meeting.getCalendar(), meeting.getUser());
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
	public Response updateMeeting(@PathParam("id") int id, Meeting meeting) {
		Meeting result= DAOMeeting.getInstance().update(id,meeting.getName(), meeting.getDateStart(), meeting.getDateEnd(), meeting.getSite(), meeting.getCalendar());
		return Response.status(201).entity(result).build();
	}
	
	@GET
	@Path("/getOverlapMeetings?userid={userid}&date1={date1}&date2={date2}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getOverlapMeetings(@QueryParam("userid")int userid, @QueryParam("date")String dateString1, @QueryParam("date")String dateString2) {
		final Date date1;
		final Date date2;
	    try {
	        date1 = new Date(dateString1); 
	        date2= new Date(dateString2);
	    } catch(Exception e) {
	        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
	        		.entity("El formato de fecha no es correcto").type(MediaType.TEXT_PLAIN).build());
	    }
		return DAOMeeting.getInstance().getOverlapMeetings(userid, date1, date2);
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

