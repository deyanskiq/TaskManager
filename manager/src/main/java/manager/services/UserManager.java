package manager.services;

import java.net.HttpURLConnection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import manager.contexts.StudentContext;
import manager.contexts.TeacherContext;
import manager.dao.StudentDAO;
import manager.dao.TeacherDAO;
import manager.enums.Speciality;
import manager.model.Student;
import manager.model.Teacher;

@Stateless
@Path("user")
public class UserManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	private static Student[] students = { new Student(61843L, "aaa", "aaa@abv.bg", "Encho", Speciality.SI),
			new Student(61863L, "bbb", "bbbb@abv.bg", "Kotyo", Speciality.KN) };

	@Inject
	private StudentDAO studentDAO;

	@Inject
	private TeacherDAO teacherDAO;

	@Inject
	private StudentContext studentContext;

	@Inject
	private TeacherContext teacherContext;

	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String loginUser(String data) throws SQLException {
		JSONObject loginData = new JSONObject(data);

		Student student = studentDAO.validateStudentCredentials(loginData.getString("name"),
				loginData.getString("password"));
		Teacher teacher = teacherDAO.validateTeacherCredentials(loginData.getString("name"),
				loginData.getString("password"));

		if (student != null) {
			studentContext.setCurrentStudent(student);
			return "Student";
		}

		if (teacher != null) {
			teacherContext.setCurrentTeacher(teacher);
			return "Teacher";
		}
		return "Not Found";

	}

	@Path("authenticated")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response isAuthenticated() {
		if (studentContext.getCurrentStudent() == null && teacherContext.getCurrentTeacher() == null) {
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build();
		}
		return RESPONSE_OK;
	}

	@Path("current")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser() {
		Student currentStudent = studentContext.getCurrentStudent();
		Teacher currentTeacher = teacherContext.getCurrentTeacher();
		if (currentStudent == null && currentTeacher == null) {
			return null;
		}

		if (currentStudent != null) {
			return currentStudent.getName();
		}
		return currentTeacher.getName();
	}

	@Path("logout")
	@GET
	public void logoutUser() {
		studentContext.setCurrentStudent(null);
		teacherContext.setCurrentTeacher(null);
	}
}
