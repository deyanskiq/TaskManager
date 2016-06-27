package manager.dao;

import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import manager.model.Specialty;

@RequestScoped
public class SpecialtyDAO {

	@PersistenceContext
	private EntityManager em;

	public void addSpeciality(Specialty specialty) throws SQLException {
		Specialty foundSpecialty = findSpecialtyByName(specialty.getName());
		if (foundSpecialty == null) {
			em.persist(specialty);
		} else {
			throw new SQLException("Specialty with name " + specialty.getName()
					+ " already exists in DB!");
		}
	}

	public Specialty findSpecialtyByName(String name) {
		TypedQuery<Specialty> query = em.createNamedQuery(
				"findSpecialtyByName", Specialty.class).setParameter("name",
				name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
