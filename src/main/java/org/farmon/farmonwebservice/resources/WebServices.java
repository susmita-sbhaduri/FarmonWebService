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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.farmon.farmondto.FarmonDTO;
import org.farmon.farmondto.FarmresourceDTO;
import org.farmon.farmondto.HarvestDTO;
import org.farmon.farmondto.LabourCropDTO;
import org.farmon.farmondto.ResourceCropDTO;
import org.farmon.farmondto.UserDTO;
import org.farmon.services.MasterDataServices;

@Path("allServices")
public class WebServices {
    
    @Path("loginAuth")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginAuthentication(String termDTOJSON) throws NamingException {        
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        }
        catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        UserDTO userDto = masterDataService.getUserAuthDetails(farmondto.getUserDto().getUsername(), 
                farmondto.getUserDto().getPassword());
        farmondto.setUserDto(userDto);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    @Path("actiHarvestList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getActiveHarvestList(String termDTOJSON) throws NamingException {        
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        }
        catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        
        List<HarvestDTO> harvests = masterDataService.getActiveHarvestList();        
        
        farmondto.setHarvestlist(harvests);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("harvestRecord")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getHarvestRecWithId(String termDTOJSON) throws NamingException {        
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        }
        catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        HarvestDTO harvest = masterDataService.getHarvestRecForId(farmondto.getHarvestrecord().
                getHarvestid());        
        
        farmondto.setHarvestrecord(harvest);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("resCropList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResCropsPerHarvest(String termDTOJSON) throws NamingException {        
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        }
        catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        List<ResourceCropDTO> rescroplist = masterDataService.getResCropForHarvest(farmondto.getHarvestrecord().getHarvestid());                        
        farmondto.setRescroplist(rescroplist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("labCropList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getLabCropsPerHarvest(String termDTOJSON) throws NamingException {        
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        }
        catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        List<LabourCropDTO> labcroplist = masterDataService.getLabCropForHarvest(farmondto.getHarvestrecord().getHarvestid());                        
        farmondto.setLabcroplist(labcroplist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("farmresourceList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getFarmresList(String termDTOJSON) throws NamingException {        
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        }
        catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        List<FarmresourceDTO> farmreslist = masterDataService.getResourceList();                        
        farmondto.setFarmresourcelist(farmreslist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
