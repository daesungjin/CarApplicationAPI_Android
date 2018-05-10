/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.resources;

import com.mycompany.carappapi.core.User;
import com.mycompany.carappapi.dao.UserDAO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author User
 */
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @GET
    public List<User> show(){
        return userDAO.findAll();
    }
    @GET
    @Path("/{userID}")
    public List<User> display(@PathParam("userID") String userID){
     return userDAO.findByUserID(userID);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<User> create(@FormParam("userID") String userID, @FormParam("userName") String userName, @FormParam("phoneNumber") String phoneNumber){
        userDAO.create(userID, userName, phoneNumber);
        return userDAO.findAll();
    }
    @DELETE
    public void delete(@FormParam("userID") String userID){
        userDAO.delete(userID);
    }
            
}
