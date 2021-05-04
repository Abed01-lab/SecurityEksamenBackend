/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.UIDDTO;
import dto.UserInfoDTO;
import facades.UserInfoFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("userinfo")
public class UserInfoResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();   
    private static final UserInfoFacade FACADE =  UserInfoFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    
    @Context
    private UriInfo context;
    
    /*@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String securedMethod() {
        return "this is secure";
    }*/
    
    @GET
    @Path("{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(@PathParam("uid") String uid) {
        return GSON.toJson(FACADE.getUserInfo(uid));
    } 
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getUserInfo(UserInfoDTO userInfoDTO) {;
        return GSON.toJson(FACADE.addUserInfo(userInfoDTO));
    }
    //get userInfo    
    //add userInfo    
    //edit userInfo
}
