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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.farmon.farmondto.ExpenseDTO;
import org.farmon.farmondto.FarmonDTO;
import org.farmon.farmondto.FarmonResponse;
import org.farmon.farmondto.FarmresourceDTO;
import org.farmon.farmondto.HarvestDTO;
import org.farmon.farmondto.LabourCropDTO;
import org.farmon.farmondto.ResAcquireDTO;
import org.farmon.farmondto.ResourceCropDTO;
import org.farmon.farmondto.ShopDTO;
import org.farmon.farmondto.ShopResDTO;
import org.farmon.farmondto.TaskPlanDTO;
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
    
    @Path("resCropMonthly")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResCropsPerMonth(String termDTOJSON) throws NamingException {        
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
        LocalDate today = LocalDate.now();

        // Get the first day of previous month
        LocalDate firstDayOfPrevMonth = today.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());

        // Get the last day of previous month
        LocalDate lastDayOfPrevMonth = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String firstDayFormatted = firstDayOfPrevMonth.format(formatter);
        String lastDayFormatted = lastDayOfPrevMonth.format(formatter);
        MasterDataServices masterDataService = new MasterDataServices();        
        List<ResourceCropDTO> rescroplist = masterDataService.getRescropCostMonthly(firstDayFormatted,
                lastDayFormatted);                        
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
    
    @Path("nonzeroresList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getNon0ResList(String termDTOJSON) throws NamingException {        
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
        List<FarmresourceDTO> farmreslist = masterDataService.getNonzeroResList();                        
        farmondto.setFarmresourcelist(farmreslist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("maxFarmresourceId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxResId(String termDTOJSON) throws NamingException {        
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
        String maxfarmresid = String.valueOf(masterDataService.getMaxFarmresId()); 
        FarmresourceDTO farmresdto = new FarmresourceDTO();
        farmresdto.setResourceId(maxfarmresid);
        farmondto.setFarmresourcerec(farmresdto);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("shopList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getShopList(String termDTOJSON) throws NamingException {        
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
        List<ShopDTO> shoplist = masterDataService.getShopList();        
        farmondto.setShoplist(shoplist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("residForName")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResidForName(String termDTOJSON) throws NamingException {        
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
        FarmresourceDTO farmresrec = masterDataService.getResourceIdForName(farmondto.getFarmresourcerec().
                getResourceName());      
        farmondto.setFarmresourcerec(farmresrec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    @Path("resnameForId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResNameForId(String termDTOJSON) throws NamingException {        
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
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        FarmresourceDTO farmresrec = masterDataService.
                getResourceNameForId(Integer.parseInt(farmondto.getFarmresourcerec().getResourceId()));    
        farmondto.setFarmresourcerec(farmresrec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("shopresList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getShopresList(String termDTOJSON) throws NamingException {        
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
        List<ShopResDTO> shopreslist = masterDataService.getResShopForPk(farmondto.getShopresrec().getResourceId(),
                farmondto.getShopresrec().getShopId());      
        farmondto.setShopreslist(shopreslist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
       
    @Path("addFarmresource")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addFarmresourceRec(String termDTOJSON) throws NamingException {        
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
        int addres = masterDataService.addResource(farmondto.getFarmresourcerec()); 
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_ADD_RES(addres);
        farmondto.setResponses(farmonres);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("editFarmresource")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editFarmresourceRec(String termDTOJSON) throws NamingException {        
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
        int editres = masterDataService.editResource(farmondto.getFarmresourcerec()); 
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_EDIT_RES(editres);
        farmondto.setResponses(farmonres);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("maxShopresId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxShopresId(String termDTOJSON) throws NamingException {        
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
        int maxid = masterDataService.getMaxIdForShopRes();      
        ShopResDTO shopresdto = new ShopResDTO();
        shopresdto.setId(String.valueOf(maxid));
        farmondto.setShopresrec(shopresdto);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("maxResAcqId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxResAcqId(String termDTOJSON) throws NamingException {        
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
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        int maxid = masterDataService.getMaxIdForResAquire();      
        ResAcquireDTO resacqdto = new ResAcquireDTO();
        resacqdto.setAcquireId(String.valueOf(maxid));
        farmondto.setResacqrec(resacqdto);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("maxExpId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxExpId(String termDTOJSON) throws NamingException {        
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
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        int maxid = masterDataService.getMaxIdForExpense();      
        ExpenseDTO expenserec = new ExpenseDTO();
        expenserec.setExpenseId(String.valueOf(maxid));
        farmondto.setExpenserec(expenserec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("expenseMonthly")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getExpenseMonth(String termDTOJSON) throws NamingException {        
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
        LocalDate today = LocalDate.now();

        // Get the first day of previous month
        LocalDate firstDayOfPrevMonth = today.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());

        // Get the last day of previous month
        LocalDate lastDayOfPrevMonth = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String firstDayFormatted = firstDayOfPrevMonth.format(formatter);
        String lastDayFormatted = lastDayOfPrevMonth.format(formatter);
        MasterDataServices masterDataService = new MasterDataServices();        
        List<ExpenseDTO> expenselist = masterDataService.getExpenseMonthly(firstDayFormatted,
                lastDayFormatted);                        
        farmondto.setExpenselist(expenselist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("addShopRes")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addShopResRec(String termDTOJSON) throws NamingException {        
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
        int addres = masterDataService.addShopResource(farmondto.getShopresrec());  
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_ADD_RES(addres);
        farmondto.setResponses(farmonres);        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("editShopRes")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editShopResRec(String termDTOJSON) throws NamingException {        
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
        int editres = masterDataService.editShopForRes(farmondto.getShopresrec());  
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_EDIT_RES(editres);
        farmondto.setResponses(farmonres);        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("addResAcq")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addResAcqRec(String termDTOJSON) throws NamingException {        
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
        int addres = masterDataService.addAcquireResource(farmondto.getResacqrec());  
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_ADD_RES(addres);
        farmondto.setResponses(farmonres);        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("delResAcq")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delResAcqRec(String termDTOJSON) throws NamingException {        
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
        int delres = masterDataService.delAcquireResource(farmondto.getResacqrec());  
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_DEL_RES(delres);
        farmondto.setResponses(farmonres);        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("addExpense")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addExpenseRec(String termDTOJSON) throws NamingException {        
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
        int addres = masterDataService.addExpenseRecord(farmondto.getExpenserec());  
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_ADD_RES(addres);
        farmondto.setResponses(farmonres);        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("delExpense")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delExpenseRec(String termDTOJSON) throws NamingException {        
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
        int delres = masterDataService.delExpenseRecord(farmondto.getExpenserec());  
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_DEL_RES(delres);
        farmondto.setResponses(farmonres);        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("delFarmresource")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delFarmresourceRec(String termDTOJSON) throws NamingException {        
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
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        int delres = masterDataService.delResource(farmondto.getFarmresourcerec());
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_DEL_RES(delres);
        farmondto.setResponses(farmonres);        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("shopresPerResid")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getShopresperResid(String termDTOJSON) throws NamingException {        
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
        }
        MasterDataServices masterDataService = new MasterDataServices();        
        List<ShopResDTO> shopreslist = masterDataService.getAllShopResForResid(farmondto.
                getShopresrec().getResourceId());
        farmondto.setShopreslist(shopreslist);        
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    @Path("taskplanList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getTaskplanList(String termDTOJSON) throws NamingException {        
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
        
        List<TaskPlanDTO> taskplanlist = masterDataService.getAllTaskPlanList();        
        
        farmondto.setTaskplanlist(taskplanlist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
