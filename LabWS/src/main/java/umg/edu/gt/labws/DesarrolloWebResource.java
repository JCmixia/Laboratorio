/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package umg.edu.gt.labws;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Isai
 */
@Path("DesarrolloWeb")
public class DesarrolloWebResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DesarrolloWebResource
     */
    public DesarrolloWebResource() {
    }

    /**
     * Retrieves representation of an instance of umg.edu.gt.labws.DesarrolloWebResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("primerWS")
    @Produces({"text/plain"})
    @Consumes({"text/plain"})
    public String primerWS(){
        //TODO return proper representation object
        return "Mi primer API WS";
    }
    
    @Path("pruebaWSParametrosJson")
    @GET
    @Consumes({"text/plain"})
    @Produces({"text/plain"})
    public String pruebaWSParametrosJson(@QueryParam("Nombre") String pNombre, @QueryParam("Apellido") String pApellido) {
        JsonObject jo = new JsonObject();
        jo.addProperty("Nombre", pNombre);
        jo.addProperty("Apellido", pApellido);
        System.out.println("el objeto json es: "+jo.toString());

        return jo.toString();
    }
    
    
     
     /* 
    public String getText(){
        //TODO return proper representation object
        return "Mi primer API WS";
    }

    /**
     * PUT method for updating or creating an instance of DesarrolloWebResource
     * @param content representation for the resource
     */
    
    /*
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
    */
  
}
