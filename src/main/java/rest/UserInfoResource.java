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
import javax.ws.rs.core.SecurityContext;
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
    private SecurityContext securityContext;
    
    //get userInfo    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserInfo() {
        String uid = securityContext.getUserPrincipal().getName();
        return GSON.toJson(FACADE.getUserInfo(uid));
    } 
    
    
    //add userInfo    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(UserInfoDTO userInfoDTO) {;
        return GSON.toJson(FACADE.addUserInfo(userInfoDTO));
    }
    //edit userInfo
    
    //get all UserInfo
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUserInfo() {
        return GSON.toJson(FACADE.getAllUserInfo());
    }
}
