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

import manager.contexts.TeacherContext;
import manager.dao.CourseDAO;
import manager.model.Course;
import manager.model.Homework;
import manager.model.Teacher;

@Stateless
@Path("courses")
public class CoursesManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private CourseDAO courseDAO;

	@Inject
	private TeacherContext teacherContext;

	@Path("addcourse")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		boolean addedCourse = courseDAO.addCourse(course);
		return addedCourse == true ? RESPONSE_OK : Response.status(Status.CONFLICT).build();
	}

	@Path("deletebyname")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteCourseByName(String name) {
		List<Course> currentTeacherCourses = teacherContext.getCurrentTeacher().getCourses();
		for (Course course : currentTeacherCourses) {
			if (course.getName().equals(name)) {
				int deleteCourseByName = courseDAO.deleteCourse(name);
				return deleteCourseByName > 0 ? RESPONSE_OK : Response.status(Status.NOT_MODIFIED).build();
			}
		}

		return Response.status(Status.NOT_MODIFIED).build();

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

	@Path("getCoursesByCurrentTeacher")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesByCurrentTeacher() {
		Teacher currentTeacher = teacherContext.getCurrentTeacher();
		System.out.println(currentTeacher);
		List<Course> courses = courseDAO.getCoursesByTeacher(currentTeacher);
		System.out.println(courses);
		return courses;
	}
}