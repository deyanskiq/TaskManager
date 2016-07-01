package manager.dao;

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

	public boolean addHomework(Homework homework) {
		Homework foundHomework = findHomeworkById(homework.getId());
		if (foundHomework == null) {
			em.persist(homework);
			return true;
		}
		return false;
	}

	public Homework findHomeworkById(Long id) {
		TypedQuery<Homework> query = em.createNamedQuery("findHomeworkById", Homework.class).setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void assessHomework(Homework homework, Double mark) {
		homework.setMark(mark);
		em.persist(homework);
	}

}
