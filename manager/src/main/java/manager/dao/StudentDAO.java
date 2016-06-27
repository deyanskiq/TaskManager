package manager.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import manager.model.Student;

@RequestScoped
public class StudentDAO {

	@PersistenceContext
	private EntityManager em;

	public void addStudent(Student student) throws SQLException {
		Student foundStudent = findStudentByFN(student.getFacultyNumber());
		if (foundStudent == null) {
			em.persist(student);
		} else {
			throw new SQLException("Student with faculty number "
					+ student.getFacultyNumber() + " already exists in DB!");
		}
	}

	private Student findStudentByFN(Long facultyNumber) {
		TypedQuery<Student> query = em.createNamedQuery("findStudentByFN",
				Student.class).setParameter("facultyNumber", facultyNumber);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Student> getAllCourses(Long facultyNumber) {
		return em.createNamedQuery("getAllCourses",Student.class).getResultList();
	}

}
