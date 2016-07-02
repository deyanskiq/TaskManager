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

import manager.dao.StudentDAO;
import manager.model.Student;

@Stateless
@Path("students")
public class StudentManager {

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

	@Path("getall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents() {
		List<Student> allStudents = studentDAO.getAllStudents();
		return allStudents;
	}

	@Path("findbyfn")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Student findStudentByFn(String facNumber) {
		Student findStudentByFN = studentDAO.findStudentByFN(Long.parseLong(facNumber));
		return findStudentByFN;
	}

	@Path("deletebyfn")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteStudentByFn(String facNumber) {
		int deleteStudentByFn = studentDAO.deleteStudentByFn(Long.parseLong(facNumber, 10));
		return deleteStudentByFn > 0 ? RESPONSE_OK : Response.status(Status.NOT_MODIFIED).build();
	}

}
