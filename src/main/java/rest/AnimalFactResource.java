package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AnimalFactDTO;
import facades.*;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AnimalFactResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final AnimalFactFacade ANIMAL_FACT_FACADE =  AnimalFactFacade.getFacadeExample(EMF);

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String animalFactCount() {
        return "{\"msg\":\"Hello World\"}";

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = ANIMAL_FACT_FACADE.getAnimalFactCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDogFact() throws ExecutionException, InterruptedException {

        return Response.ok().entity(GSON.toJson(ANIMAL_FACT_FACADE.getAnimalFact("dog"))).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFoxFact() throws ExecutionException, InterruptedException {

      return Response.ok().entity(GSON.toJson(ANIMAL_FACT_FACADE.getAnimalFact("fox"))).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getKoalaFact() throws ExecutionException, InterruptedException {

        return Response.ok().entity(GSON.toJson(ANIMAL_FACT_FACADE.getAnimalFact("koala"))).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCatFact() throws ExecutionException, InterruptedException {

        return Response.ok().entity(GSON.toJson(ANIMAL_FACT_FACADE.getAnimalFact("cat"))).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRandomFact() throws ExecutionException, InterruptedException {

        return Response.ok().entity(GSON.toJson(ANIMAL_FACT_FACADE.getAnimalFact("random"))).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response saveFactToHistory(@PathParam("user")String user, String factDTO){
        AnimalFactDTO animalFactDTO = GSON.fromJson(factDTO,AnimalFactDTO.class);
        ANIMAL_FACT_FACADE.addFactToHistory(user, animalFactDTO);

        return Response.ok().entity(GSON.toJson(animalFactDTO)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response factHistory(@PathParam("user")String user){

        List<AnimalFactDTO> animalFactDTOList = ANIMAL_FACT_FACADE.getFactHistory(user);
        return Response.ok().entity( GSON.toJson(animalFactDTOList)).build();


    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response saveFactToSavedFacts(@PathParam("user")String user, String factDTO){
        AnimalFactDTO animalFactDTO = GSON.fromJson(factDTO,AnimalFactDTO.class);
        ANIMAL_FACT_FACADE.addFactToSavedFacts(user, animalFactDTO);

        return Response.ok().entity(GSON.toJson(animalFactDTO)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response savedFacts(@PathParam("user")String user){

        List<AnimalFactDTO> animalFactDTOList = ANIMAL_FACT_FACADE.getSavedFacts(user);
        return Response.ok().entity( GSON.toJson(animalFactDTOList)).build();


    }



}
