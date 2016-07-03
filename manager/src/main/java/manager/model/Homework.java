package manager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@XmlRootElement
@Table(name = "HOMEWORKS")
@NamedQueries({ @NamedQuery(name = "findHomeworkById", query = "SELECT h FROM Homework h WHERE h.id=:id"),
		@NamedQuery(name = "getAllHomeworks", query = "SELECT h FROM Homework h") })
public class Homework implements Serializable {

	private static final long serialVersionUID = 2630716586806811332L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Lob
	private String description;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Course course;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "homework", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Task> tasks = new ArrayList<>();

	public Homework() {

	}

	public Homework(Course course, String name, String description, Date deadline) {
		this.setCourse(course);
		this.name = name;
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

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "Homework [name=" + name + ", description=" + description + ", deadline=" + deadline + "]";
	}

}
