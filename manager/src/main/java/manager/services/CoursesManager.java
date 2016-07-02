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
import manager.model.Homework;

@Stateless
@Path("courses")
public class CoursesManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private CourseDAO courseDAO;

	@Path("addcourse")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		boolean addedCourse = courseDAO.addCourse(course);
		return addedCourse == true ? RESPONSE_OK : Response.status(Status.CONFLICT).build();
	}

	@Path("getall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCourses() {
		List<Course> courses = courseDAO.getCourses();
		return courses;
	}

	@Path("gethwsforcourse")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Homework> getHomeworksForACourse(String courseName) {
		List<Homework> allHomeworksForCourse = courseDAO.getAllHomeworksForCourse(courseName);
		return allHomeworksForCourse;
	}

	@Path("findbyname")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Course findCourseByName(String courseName) {
		Course findCourseByName = courseDAO.findCourseByName(courseName);
		return findCourseByName;
	}
}