package manager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "COURSES")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer credits;

	@Lob
	private String description;

	public Course(String name, Integer credits, String description) {
		this.name = name;
		this.credits = credits;
		this.description = description;
	}

	public Course() {

	}

	public String getName() {
		return name;
	}

	public Integer getCredits() {
		return credits;
	}

	public String getDescription() {
		return description;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((credits == null) ? 0 : credits.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Course other = (Course) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (credits == null) {
			if (other.credits != null)
				return false;
		} else if (!credits.equals(other.credits))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", credits=" + credits + ", description=" + description + ", courses=" + courses
				+ "]";
	}

}
