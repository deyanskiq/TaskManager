package manager.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import manager.model.Course;
import manager.model.Homework;

@RequestScoped
public class CourseDAO {

	@PersistenceContext
	private EntityManager em;

	public void addCourse(Course course) throws SQLException {
		Course foundCourse = findCourseByName(course.getName());
		if (foundCourse == null) {
			em.persist(course);

		} else {
			throw new SQLException("Course with name " + course.getName() + " already exists in DB!");
		}
	}

	public Course findCourseByName(String name) {
		TypedQuery<Course> query = em.createNamedQuery("findCourseByName", Course.class).setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Homework> getAllHomeworksForCourse(String courseName) {
		Course course = findCourseByName(courseName);
		if (course != null) {
			return course.getHomeworks();
		}
		return null;
	}
}
