package manager.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import manager.model.Specialty;

@RequestScoped
public class SpecialityDAO {

	@PersistenceContext
	private EntityManager em;

	public void addSpeciality(Specialty speciality) {
		em.persist(speciality);
	}

}
