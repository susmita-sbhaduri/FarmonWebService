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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.farmon.farmondto.MyData;

@Path("loginCheck")
public class ExampleService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public String postExample(MyData inputData) {
        // Here you get the POJO sent from the client (firstDTO)

        // For testing, modify or add some value before returning
        inputData.setPassword("Response value set by server");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(inputData);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
