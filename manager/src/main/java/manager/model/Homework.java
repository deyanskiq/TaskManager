package manager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
	@NamedQuery(name="findHomeworkById", query="SELECT h FROM Homework h WHERE h.id=:id")
})
@Table(name = "HOMEWORKS")
public class Homework implements Serializable {

	private static final long serialVersionUID = 2630716586806811332L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String description;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Course course;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Student student;

	private Integer mark;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;

	public Homework() {

	}

	public Homework(String description, Date deadline) {
		this.description = description;
		this.deadline = deadline;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Course getCourse() {
		return course;
	}

	public Student getStudent() {
		return student;
	}

	public Integer getMark() {
		return mark;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Homework other = (Homework) obj;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Homework [description=" + description + ", course=" + course + ", student=" + student + ", mark=" + mark
				+ ", deadline=" + deadline + "]";
	}

}
