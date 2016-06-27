package manager.dao;

import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import manager.model.Homework;

@RequestScoped
public class HomeworkDAO {

	@PersistenceContext
	EntityManager em;
	
	public void addHomework(Homework homework) throws SQLException {
		Homework foundHomework = findHomeworkById(homework.getId());
		if(foundHomework==null){
			em.persist(homework);
		} else {
			throw new SQLException("Homework with id " + homework.getId() + "already exists in DB!");
		}
	}
	
	public Homework findHomeworkById(Long id) {
		TypedQuery<Homework> query = em.createNamedQuery("findHomeworkById",
				Homework.class).setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
