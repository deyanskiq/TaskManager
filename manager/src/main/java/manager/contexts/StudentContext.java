package manager.contexts;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import manager.model.Student;

@SessionScoped
public class StudentContext implements Serializable {

	private static final long serialVersionUID = -5185469629320384569L;

	private Student currentStudent;

	public Student getCurrentStudent() {
		return currentStudent;
	}

	public void setCurrentStudent(Student currentUser) {
		this.currentStudent = currentUser;
	}

}
