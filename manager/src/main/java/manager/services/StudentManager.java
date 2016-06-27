package manager.services;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import manager.dao.StudentDAO;
import manager.model.Student;

@Stateless
@Path("students")
public class StudentManager {

	private static Student[] students = { new Student(61843L, "aaa", "aaa@abv.bg", "Encho"),
			new Student(61863L, "bbb", "bbbb@abv.bg", "Kotyo") };

	@Inject
	private StudentDAO studentDAO;

	@GET
	@Path("getall")
	public void getStudents() throws SQLException {
		System.out.println(studentDAO == null);
		for (Student student : students) {
			studentDAO.addStudent(student);
		}
		System.out.println(studentDAO.findStudentByFN(61843L));
	}

}
