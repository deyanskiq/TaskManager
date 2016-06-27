package manager.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import manager.model.Student;

@RequestScoped
public class StudentDAO {

	@PersistenceContext
	private EntityManager em;

	public void addStudent(Student student) {
		em.persist(student);
	}

}
