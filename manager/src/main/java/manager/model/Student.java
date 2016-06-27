package manager.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "STUDENTS")
public class Student implements Serializable {

	private static final long serialVersionUID = 424427115758866372L;

	@Id
	private Long facultyNumber;

	private String password;

	private String email;

	private String name;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Specialty speciality;

	public Student(Long facultyNumber, String password, String email, String name, Specialty speciality) {
		this.facultyNumber = facultyNumber;
		this.password = password;
		this.email = email;
		this.name = name;
		this.speciality = speciality;
	}

	public Student() {

	}

	public Long getFacultyNumber() {
		return facultyNumber;
	}

	public void setFacultyNumber(Long facultyNumber) {
		this.facultyNumber = facultyNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Specialty getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Specialty speciality) {
		this.speciality = speciality;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((facultyNumber == null) ? 0 : facultyNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (facultyNumber == null) {
			if (other.facultyNumber != null)
				return false;
		} else if (!facultyNumber.equals(other.facultyNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [facultyNumber=" + facultyNumber + ", password=" + password + ", email=" + email + ", name="
				+ name + ", speciality=" + speciality + "]";
	}
}
