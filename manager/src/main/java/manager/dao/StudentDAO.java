package manager.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import manager.model.Course;
import manager.model.Homework;
import manager.model.Student;

@Singleton
public class StudentDAO {

	@PersistenceContext(unitName = "manager")
	private EntityManager em;

	public void addStudent(Student student) throws SQLException {
		// Student foundStudent = findStudentByFN(student.getFacultyNumber());
		// if (foundStudent == null) {
		System.out.println(em == null);
		em.persist(student);
		// } else {
		// throw new SQLException(
		// "Student with faculty number " + student.getFacultyNumber() + "
		// already exists in DB!");
		// }
	}

	public Student findStudentByFN(Long facultyNumber) {
		TypedQuery<Student> query = em.createNamedQuery("findStudentByFN", Student.class).setParameter("facultyNumber",
				facultyNumber);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Course> getAllCourses(Long facultyNumber) {
		Student s = findStudentByFN(facultyNumber);
		if (s != null) {
			return s.getCourses();
		}
		return null;
	}

	public List<Homework> getAllHomeworks(Long facultyNumber) {
		Student s = findStudentByFN(facultyNumber);
		if (s != null) {
			return s.getHomeworks();
		}
		return null;
	}

}
