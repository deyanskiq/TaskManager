package manager.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

	public int deleteHomework(String name) {
		Query query = em.createQuery("DELETE FROM Homework h WHERE h.name=:name").setParameter("name", name);
		return query.executeUpdate();

	}

	public List<Homework> getAllHomeworks() {
		TypedQuery<Homework> query = em.createNamedQuery("getAllHomeworks", Homework.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
