/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AddClassToCourseDTO;
import dto.CourseDTO;
import entities.Course;
import facades.CourseFacade;
import java.util.List;
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
@Path("course")
public class CourseResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();   
    private static final CourseFacade FACADE =  CourseFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Working\"}";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addCourse(CourseDTO courseDTO){
        FACADE.addCourse(courseDTO);
        return "\"{\\\"msg\\\":\\\"Update Succesfull\\\"}\"";
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCourses(){
        return GSON.toJson(FACADE.getListCourses());
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateCourse(CourseDTO courseDTO){
        FACADE.updateCourse(courseDTO);
        return "\"{\\\"msg\\\":\\\"Update Succesfull\\\"}\"";
    }
    
    @POST
    @Path("addclass")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addClassToCourse(AddClassToCourseDTO addClassToCourseDTO){
        FACADE.addClassToCourses(addClassToCourseDTO);
        return "\"{\\\"msg\\\":\\\"Update Succesfull\\\"}\"";
    }
}
