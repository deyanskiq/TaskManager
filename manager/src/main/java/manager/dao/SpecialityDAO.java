package manager.dao;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import manager.model.Specialty;

@Singleton
public class SpecialityDAO {

	@PersistenceContext(unitName = "manager")
	private EntityManager em;

	public void addSpeciality(Specialty speciality) {
		em.persist(speciality);
	}

}
