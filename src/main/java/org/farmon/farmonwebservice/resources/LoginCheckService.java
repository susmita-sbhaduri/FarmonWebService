/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.farmonwebservice.resources;

import jakarta.ws.rs.POST;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.farmon.farmondto.UserDTO;
import org.farmon.services.MasterDataServices;

@Path("loginAuth")
public class LoginCheckService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public String loginAuthentication(String termDTOJSON) throws NamingException {
        
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userdto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, UserDTO.class);
             userdto = (UserDTO) DTO;
        }
        catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            userdto = new UserDTO();
            userdto.setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        userdto = masterDataService.getUserAuthDetails(userdto.getUsername(), userdto.getPassword());
        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(userdto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
