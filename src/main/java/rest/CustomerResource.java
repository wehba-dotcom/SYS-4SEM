package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CustomerCoursesDTO;
import dtos.GoalDTO;
import facades.CustomerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
