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

import org.json.JSONArray;

import manager.dao.StudentDAO;
import manager.enums.Speciality;
import manager.model.Student;

@Stateless
@Path("students")
public class StudentManager {

	private static Student[] students = { new Student(61843L, "aaa", "aaa@abv.bg", "Encho", Speciality.SI),
			new Student(61863L, "bbb", "bbbb@abv.bg", "Kotyo", Speciality.KN) };

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private StudentDAO studentDAO;

	@Path("addstudent")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerStudent(Student student) {
		boolean added = studentDAO.addStudent(student);
		return added == true ? RESPONSE_OK : Response.status(Status.NOT_ACCEPTABLE).build();

	}

	@GET
	@Path("getall")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStudents() {
		List<Student> allStudents = studentDAO.getAllStudents();
		if (allStudents != null) {
			JSONArray jsonStudents = new JSONArray(allStudents);
			return jsonStudents.toString();
		}
		return null;
	}

}
