package manager.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import manager.dao.HomeworkDAO;
import manager.model.Homework;
import manager.utills.HomeworkData;

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
	}

}
