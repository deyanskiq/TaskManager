package manager.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import manager.contexts.TeacherContext;
import manager.model.Course;
import manager.model.Homework;
import manager.model.Teacher;

@RequestScoped
public class CourseDAO {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private TeacherContext teacherContext;

	public boolean addCourse(Course course) {

		Course foundCourse = findCourseByName(course.getName());
		if (foundCourse == null) {
			course.setTeacher(teacherContext.getCurrentTeacher());
			em.persist(course);
			return true;
		}
		return false;
	}

	public int deleteCourse(String name) {
		Query query = em.createQuery("DELETE FROM Course c WHERE c.name=:name").setParameter("name", name);
		return query.executeUpdate();

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

	public List<Course> getCourses() {
		TypedQuery<Course> query = em.createNamedQuery("getCourses", Course.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Course> getCoursesByTeacher(Teacher teacher) {
		Query query = em.createQuery("SELECT c FROM Course c WHERE c.teacher = :teacher").setParameter("teacher",
				teacher);
		return query.getResultList();
	}
}