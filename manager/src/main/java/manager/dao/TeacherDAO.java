package manager.dao;

import java.security.MessageDigest;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import manager.model.Teacher;

@RequestScoped
public class TeacherDAO {

	@PersistenceContext
	private EntityManager em;

	public boolean addTeacher(Teacher teacher) {
		teacher.setPassword(getHashedPassword(teacher.getPassword()));
		Teacher foundTeacher = findTeacherByNameAndPass(teacher.getName(), teacher.getPassword());
		if (foundTeacher == null) {
			em.persist(teacher);
			return true;
		}
		return false;
	}

	public Teacher findTeacherByNameAndPass(String name, String password) {
		TypedQuery<Teacher> query = em.createNamedQuery("findTeacherByNameAndPass", Teacher.class)
				.setParameter("name", name).setParameter("password", password);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Teacher findTeacherByUserName(String username) {
		TypedQuery<Teacher> query = em.createNamedQuery("findTeacherByUserName", Teacher.class).setParameter("username",
				username);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Teacher validateTeacherCredentials(String name, String password) {
		String txtQuery = "SELECT t FROM Teacher t WHERE t.name=:name AND t.password=:password";
		TypedQuery<Teacher> query = em.createQuery(txtQuery, Teacher.class);
		query.setParameter("name", name);
		query.setParameter("password", getHashedPassword(password));
		Teacher teacher = queryUser(query);
		return teacher != null ? teacher : null;

	}

	private Teacher queryUser(TypedQuery<Teacher> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private String getHashedPassword(String password) {
		try {
			MessageDigest mda = MessageDigest.getInstance("SHA-512");
			password = new String(mda.digest(password.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	public int deleteTeacherByUsername(String username) {
		Query query = em.createQuery("DELETE FROM Teacher t WHERE t.username=:username").setParameter("username",
				username);
		return query.executeUpdate();

	}

}