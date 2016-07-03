package manager.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@XmlRootElement
@Table(name = "TASKS")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSubmitted;

	@Lob
	private Blob solution;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Student student;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Homework homework;

	private Double mark;

	public Task(Date dateSubmitted, Blob solution) {
		this.dateSubmitted = dateSubmitted;
		this.solution = solution;
	}

	public Task() {

	}

	public Long getId() {
		return id;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public Blob getSolution() {
		return solution;
	}

	public Student getStudent() {
		return student;
	}

	public Homework getHomework() {
		return homework;
	}

	public Double getMark() {
		return mark;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public void setSolution(Blob solution) {
		this.solution = solution;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Task [dateSubmitted=" + dateSubmitted + ", mark=" + mark + "]";
	}

}
