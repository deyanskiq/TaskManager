package manager.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import manager.model.Specialty;

@Stateless
public class SpecialityDAO {

	@PersistenceContext
	private EntityManager em;

	public void addSpeciality(Specialty speciality) {
		em.persist(speciality);
	}

}
