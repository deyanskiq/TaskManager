package manager.utills;

import manager.model.Homework;

public class HomeworkData {

	private Homework homework;

	private Double mark;

	public HomeworkData(Homework homework, Double mark) {
		this.homework = homework;
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

	@Override
	public String toString() {
		return "HomeworkData [homework=" + homework + ", mark=" + mark + "]";
	}

}
