package manager.dao;

import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import manager.model.Teacher;

@RequestScoped
public class TeacherDAO {

	@PersistenceContext
	EntityManager em;
	
	public void addTeacher(Teacher teacher) throws SQLException {
		Teacher foundTeacher = findTeacherByName(teacher.getName());
		if(foundTeacher==null){
			em.persist(teacher);
		} else {
			throw new SQLException("Teacher with name " + teacher.getName() + " already exists in DB!");
		}
	}
	
	public Teacher findTeacherByName(String name) {
		TypedQuery<Teacher> query = em.createNamedQuery("findTeacherByName",
				Teacher.class).setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
