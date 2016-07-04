package manager.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import manager.contexts.TeacherContext;
import manager.dao.HomeworkDAO;
import manager.model.Course;
import manager.model.Homework;
import manager.model.Teacher;

@Stateless
@Path("homeworks")
public class HomeworkManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private HomeworkDAO homeworkDAO;

	@Inject
	private TeacherContext teacherContext;

	@Path("addhomework")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addHomework(Homework homework) {
		boolean addHomework = homeworkDAO.addHomework(homework);
		for (Course course : teacherContext.getCurrentTeacher().getCourses()) {
			if (course.equals(homework.getCourse())) {
				course.getHomeworks().add(homework);
			}
		}
		// System.out.println(homework.getCourse());
		return addHomework == true ? RESPONSE_OK : Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@Path("deleteByName")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteHomeworkByName(String name) {
		// System.out.println(teacherContext.getCurrentTeacher());

		List<Course> currentTeacherCourses = teacherContext.getCurrentTeacher().getCourses();
		// System.out.println(currentTeacherCourses);
		for (Course course : currentTeacherCourses) {
			List<Homework> homeworks = course.getHomeworks();
			// System.out.println(homeworks);
			for (Homework homework : homeworks) {
				if (homework.getName().equals(name)) {
					course.getHomeworks().remove(homework);
					int deleteHomeworkByName = homeworkDAO.deleteHomework(name);
					return deleteHomeworkByName > 0 ? RESPONSE_OK : Response.status(Status.NOT_MODIFIED).build();
				}
			}
		}

		return Response.status(Status.NOT_MODIFIED).build();

	}

	@Path("getall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Homework> getAllCourses() {
		List<Homework> homeworks = new LinkedList<>();
		Teacher currentTeacher = teacherContext.getCurrentTeacher();
		for (Course course : currentTeacher.getCourses()) {
			homeworks.addAll(course.getHomeworks());
		}
		// List<Homework> courses = homeworkDAO.getAllHomeworks();
		return homeworks;
	}

	@Path("sendmail")
	@GET
	public void sendMail() {
		final String username = "homeworkmanager2@gmail.com";
		final String password = "taskmanage";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("homeworkmanager2@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("polina.krusteva.95@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}