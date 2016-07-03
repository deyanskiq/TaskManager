package manager.utills;

import manager.model.Homework;
import manager.model.Student;

public class HomeworkData {

	private Homework homework;

	private Student student;

	private Double mark;

	public HomeworkData(Homework homework, Student student, Double mark) {
		this.homework = homework;
		this.student = student;
		this.mark = mark;
	}

	public HomeworkData() {

	}

	public Homework getHomework() {
		return homework;
	}

	public Double getMark() {
		return mark;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "HomeworkData [homework=" + homework + ", mark=" + mark + "]";
	}

}
