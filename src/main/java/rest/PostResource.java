/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PostDTOWithUid;
import facades.PostFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author abedh
 */
@Path("post")
public class PostResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();   
    private static final PostFacade FACADE =  PostFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of PostResource
     */
    public PostResource() {
    }

    //Get all post
    @GET
    @Path("{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPost(@PathParam("uid") String uid){
        return GSON.toJson(FACADE.getAllPost(uid));
    }
    
    //add a post
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addAPost(PostDTOWithUid postDTO){
        return GSON.toJson(FACADE.addPost(postDTO));
    }
}
