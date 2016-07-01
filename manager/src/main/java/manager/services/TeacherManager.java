package manager.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import manager.dao.TeacherDAO;
import manager.model.Teacher;

@Path("teachers")
public class TeacherManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private TeacherDAO teacherDAO;

	@Path("addteacher")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerTeacher(Teacher teacher) {
		boolean added = teacherDAO.addTeacher(teacher);
		return added == true ? RESPONSE_OK : Response.status(Status.NOT_ACCEPTABLE).build();
	}

}
