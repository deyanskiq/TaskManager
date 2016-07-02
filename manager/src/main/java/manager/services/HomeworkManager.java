package manager.services;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import manager.dao.HomeworkDAO;
import manager.model.Homework;
import manager.model.Student;
import manager.utills.HomeworkData;

@Stateless
@Path("homeworks")
public class HomeworkManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private HomeworkDAO homeworkDAO;

	@Path("addhomework")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addHomework(Homework homework) {
		boolean addHomework = homeworkDAO.addHomework(homework);
		return addHomework == true ? RESPONSE_OK : Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@Path("assess")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void assessHomework(HomeworkData homeworkData) {
		homeworkDAO.assessHomework(homeworkData.getHomework(), homeworkData.getMark());
		Student student = homeworkData.getHomework().getStudent();
		String email = student.getEmail();

		final String username = "your_user_name@gmail.com";
		final String password = "yourpassword";

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
			message.setFrom(new InternetAddress("vladislavatanasov95@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Your Homework was assessed");
			message.setText("Dear" + student.getName() + ",\n\n Your mark for homework "
					+ homeworkData.getHomework().getDescription() + " is " + homeworkData.getMark() + "!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
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