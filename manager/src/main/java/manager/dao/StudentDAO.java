package manager.dao;

import java.security.MessageDigest;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import manager.model.Course;
import manager.model.Homework;
import manager.model.Student;

@RequestScoped
public class StudentDAO {

	@PersistenceContext
	private EntityManager em;

	public boolean addStudent(Student student) {
		student.setPassword(getHashedPassword(student.getPassword()));
		Student foundStudent = findStudentByFN(student.getFacultyNumber());
		if (foundStudent == null) {
			em.persist(student);
			return true;
		}
		return false;
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

	public List<Course> getAllCoursesByFN(Long facultyNumber) {
		Student s = findStudentByFN(facultyNumber);
		if (s != null) {
			return s.getCourses();
		}
		return null;
	}

	public List<Homework> getAllHomeworksByFN(Long facultyNumber) {
		Student s = findStudentByFN(facultyNumber);
		if (s != null) {
			return s.getHomeworks();
		}
		return null;
	}

	public Student validateStudentCredentials(String name, String password) {
		String txtQuery = "SELECT s FROM Student s WHERE s.name=:name AND s.password=:password";
		TypedQuery<Student> query = em.createQuery(txtQuery, Student.class);
		query.setParameter("name", name);
		query.setParameter("password", getHashedPassword(password));
		Student student = queryUser(query);
		return student != null ? student : null;

	}

	public List<Student> getAllStudents() {
		TypedQuery<Student> query = em.createNamedQuery("getStudents", Student.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	private Student queryUser(TypedQuery<Student> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private String getHashedPassword(String password) {
		try {
			MessageDigest mda = MessageDigest.getInstance("SHA-512");
			password = new String(mda.digest(password.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	public int deleteStudentByFn(long facultyNumber) {
		Query query = em.createQuery("DELETE FROM Student s WHERE s.facultyNumber=:facultyNumber")
				.setParameter("facultyNumber", facultyNumber);
		return query.executeUpdate();
	}

}
