package manager.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import manager.model.Homework;
import manager.model.Student;
import manager.model.Task;

@RequestScoped
public class TaskDAO {

	@PersistenceContext
	private EntityManager em;

	public void addTask(Task task) {
		em.persist(task);
	}

	public boolean assessTask(Homework homework, Student student, Double mark) {
		List<Task> tasks = student.getTasks();
		Task taskToAssess = null;
		for (Task task : tasks) {
			if (task.getHomework().getDescription().equals(homework.getDescription())
					&& task.getStudent().getFacultyNumber().equals(student.getFacultyNumber())) {
				taskToAssess = task;
				taskToAssess.setMark(mark);
				em.persist(taskToAssess);
				return true;
			}
		}
		return false;
	}

	public List<Task> getAllTasksByStudent(Student student) {
		Query query = em.createQuery("SELECT t FROM Task t WHERE t.student = :student").setParameter("student",
				student);
		return query.getResultList();
	}
}
