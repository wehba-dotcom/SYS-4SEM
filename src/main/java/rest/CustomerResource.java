package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CustomerCoursesDTO;
import dtos.GoalDTO;
import facades.CustomerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customer")
public class CustomerResource
{

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final CustomerFacade customerFacade = CustomerFacade.getCustomerFacade(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll()
    {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    @GET
    @Path("goal/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getGoalById(@PathParam("id") int id)
    {
        GoalDTO goalDTO = customerFacade.getGoalById(id);
        return Response.ok().entity(GSON.toJson(goalDTO)).build();
    }

    @GET
    @Path("customercourses/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCustomerCourses(@PathParam("id") int id)
    {
        List<CustomerCoursesDTO> customerCoursesDTOList = customerFacade.getCustomerCourses(id);
        return Response.ok().entity(GSON.toJson(customerCoursesDTOList)).build();
    }

    @POST
    @Path("setgoal/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response setGoalToCustomerCourse(@PathParam("id")int id, String cg)
    {

        GoalDTO goalDTO = GSON.fromJson(cg, GoalDTO.class);
        String result = customerFacade.createGoalOnCustomerCourse(goalDTO, id);
        return Response.ok().entity(result).build();



    }
}
