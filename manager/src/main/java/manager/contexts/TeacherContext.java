package manager.contexts;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import manager.model.Teacher;

@SessionScoped
public class TeacherContext implements Serializable {

	private static final long serialVersionUID = -7273252171372802403L;

	private Teacher currentTeacher;

	public Teacher getCurrentTeacher() {

		return currentTeacher;
	}

	public void setCurrentTeacher(Teacher currentTeacher) {
		this.currentTeacher = currentTeacher;
	}

}
