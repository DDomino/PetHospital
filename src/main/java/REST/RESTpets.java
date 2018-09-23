/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Entities.Pet;
import Entities.PetDTO;
import Facade.FacadePet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Andreas
 */
@Path("pets")
public class RESTpets {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_PetHospital_war_1.0-SNAPSHOTPU");

    Gson gson;
    FacadePet fp = new FacadePet(emf);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public RESTpets() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves representation of an instance of REST.RESTpets
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response countPets() {

        List<Pet> pets = fp.getPets();

        return Response.ok("{\"petCount\":\"" + pets.size() + "\"]").build();
    }
    
    @Path("alive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlivePets(){
    List<PetDTO> pdto = fp.getPetsAliveDTO();
    String json =gson.toJson(pdto);
    
        
        return Response.ok(pdto).build();
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPets() {
        List<PetDTO> pdto = fp.getPetsDTO();
        String json = gson.toJson(pdto);

        return Response.ok(json).build();
    }
}



