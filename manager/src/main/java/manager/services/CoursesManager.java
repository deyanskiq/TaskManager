package manager.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import manager.dao.CourseDAO;
import manager.model.Course;

@Stateless
@Path("courses")
public class CoursesManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	private static Course[] courses = { new Course("Operation Systems", 6, "VELIN"),
			new Course("Databases", 3, "Randoma"), new Course("Computer Networks", 5, "Dancho") };

	@Inject
	private CourseDAO courseDAO;

	@POST
	@Path("addcourse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		boolean addedCourse = courseDAO.addCourse(course);
		return addedCourse == true ? RESPONSE_OK : Response.status(Status.CONFLICT).build();
	}

	@GET
	@Path("getall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCourses() {
		for (Course course : courses) {
			this.addCourse(course);
		}

		List<Course> courses = courseDAO.getCourses();
		return courses != null ? courses : null;
	}

}
