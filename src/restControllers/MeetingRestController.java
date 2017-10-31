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
import login.Secured;
import services.DAOMeeting;
import services.MeetingRest;

@Path("/meetings")
public class MeetingRestController {

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getUsers() {
		List<Meeting> result = DAOMeeting.getInstance().getMeetings();
		return result;
	}

	@GET
	@Secured
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
	public Response createMeeting(MeetingRest meeting) {
		final Date date1;
		final Date date2;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
			date1 = formatter.parse(meeting.getDateStart()); 
			date2= formatter.parse(meeting.getDateEnd());
		} catch(Exception e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("El formato de fecha no es correcto").type(MediaType.TEXT_PLAIN).build());
		}
		Meeting result= DAOMeeting.getInstance().createMeeting(meeting.getName(), date1, date2,meeting.getIdSite() ,meeting.getIdCalendar(),
				meeting.getPersonal(),meeting.getRemember());
		return Response.status(201).entity(result).build();

	}

	@DELETE
	@Secured
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
	@Secured
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMeeting(@PathParam("id") int id, MeetingRest meeting) {
		final Date date1;
		final Date date2;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
			date1 = formatter.parse(meeting.getDateStart()); 
			date2= formatter.parse(meeting.getDateEnd());
		} catch(Exception e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("El formato de fecha no es correcto").type(MediaType.TEXT_PLAIN).build());
		}
		Meeting result= DAOMeeting.getInstance().update(id,date1,date2, meeting);
		return Response.status(201).entity(result).build();
	}

	@GET
	@Secured
	@Path("/getMeetingsByUserAndDay")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getMeetingsByUserAndDay(@QueryParam("idUser")int idUser, @QueryParam("date")String dateString) {
		final Date date;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
			date = formatter.parse(dateString); 
		} catch(Exception e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("El formate de fecha no es correcto").type(MediaType.TEXT_PLAIN).build());
		}
		return DAOMeeting.getInstance().getMeetingsByUserAndDay(idUser, date);
	}

	@GET
	@Secured
	@Path("/getMeetingsByUserBetweenDates")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getMeetingsByUserBetweenDates(@QueryParam("idUser")int idUser, @QueryParam("date1")String dateString1, @QueryParam("date2")String dateString2) {
		final Date date1;
		final Date date2;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
			date1 = formatter.parse(dateString1); 
			date2= formatter.parse(dateString2);
		} catch(Exception e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("El formate de fecha no es correcto").type(MediaType.TEXT_PLAIN).build());
		}
		return DAOMeeting.getInstance().getMeetingsByUserBetweenDates(idUser, date1, date2);
	}

	@GET
	@Secured
	@Path("/getOverlapMeetings")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meeting> getOverlapMeetings(@QueryParam("idUser")int idUser, @QueryParam("date1")String dateString1, @QueryParam("date2")String dateString2) {
		final Date date1;
		final Date date2;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
			date1 = formatter.parse(dateString1); 
			date2= formatter.parse(dateString2);
		} catch(Exception e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("El formato de fecha no es correcto").type(MediaType.TEXT_PLAIN).build());
		}
		return DAOMeeting.getInstance().getOverlapMeetings(idUser, date1, date2);
	}

	public class RecursoDuplicado extends WebApplicationException {
		private static final long serialVersionUID = 1L;

		public RecursoDuplicado(int id) {
			super(Response.status(Response.Status.CONFLICT)
					.entity("El recurso con ID "+id+" ya existe").type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class RecursoNoExiste extends WebApplicationException {
		private static final long serialVersionUID = 1L;

		public RecursoNoExiste(int id) {
			super(Response.status(Response.Status.NOT_FOUND)
					.entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
		}
	}
}

