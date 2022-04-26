package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.UserDTO;
import entities.User;
import errorhandling.API_Exception;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final UserFacade USER_FACADE = UserFacade.getUserFacade(EMF);

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String userPath() {
        return "{\"msg\":\"this is the User api\"}";

    }


    @Path("create")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createUser(String jsonString) throws API_Exception {
        String username;
        String password;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            username = json.get("username").getAsString();
            password = json.get("password").getAsString();
            UserDTO userDTO = new UserDTO(username, password);

            USER_FACADE.createUser(userDTO);
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Supplied", 400, e);
        }
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("username", username);
        responseJson.addProperty("success", Boolean.TRUE);


        return Response.ok(new Gson().toJson(responseJson)).build();
    }


}
