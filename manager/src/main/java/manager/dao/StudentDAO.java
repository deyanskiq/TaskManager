package manager.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import manager.model.Student;

@Stateless
public class StudentDAO {

	@PersistenceContext
	private EntityManager em;

	public void addStudent(Student student) {
		em.persist(student);
	}

}
