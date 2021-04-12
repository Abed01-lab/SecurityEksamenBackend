/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AddTeacherToClassDTO;
import dto.CourseClassDTO;
import facades.CourseClassFacade;
import facades.CourseFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author abed
 */
@Path("class")
public class CourseClassResource {

    @Context
    private UriInfo context;
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();   
    private static final CourseClassFacade FACADE =  CourseClassFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of CourseClassResource
     */
    public CourseClassResource() {
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Working\"}";
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String allClasses(){
        return GSON.toJson(FACADE.getAllClasses());
    }
    
    @GET
    @Path("allwithinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String allClassesWithInfo(){
        return GSON.toJson(FACADE.getAllClassesWithInfo());
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addClass(CourseClassDTO courseClassDTO){
        FACADE.addClass(courseClassDTO);
        return "{\"msg\":\"Added Succesfully\"}";
    }
    
    @POST
    @Path("addteacher")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addTeacherToClass(AddTeacherToClassDTO id){
        FACADE.addTeacherToClass(id);
        return "{\"msg\":\"Added Succesfully\"}";
    }

}
