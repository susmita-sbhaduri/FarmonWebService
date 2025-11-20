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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.farmon.farmondto.AllExpenseReportDTO;
import org.farmon.farmondto.EmpExpDTO;
import org.farmon.farmondto.EmpLeaveDTO;
import org.farmon.farmondto.EmployeeDTO;
import org.farmon.farmondto.ExpenseDTO;
import org.farmon.farmondto.FarmonDTO;
import org.farmon.farmondto.FarmonResponse;
import org.farmon.farmondto.FarmresourceDTO;
import org.farmon.farmondto.HarvestDTO;
import org.farmon.farmondto.LabourCropDTO;
import org.farmon.farmondto.ResAcqReportDTO;
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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

    @Path("resCropPerRes")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResCropPerRes(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        List<ResourceCropDTO> rescroplist = masterDataService.getResCropForResource(farmondto.
                getResourceCropDTO().getResourceId());
        farmondto.setRescroplist(rescroplist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("resCropPerResDt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResCropPerResDt(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        Date startDate;
        Date endDate;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        startDate = formatter.parse(farmondto.getReportstartdt());
        endDate = formatter.parse(farmondto.getReportenddt());
        List<ResourceCropDTO> rescroplist = masterDataService
                .getRescropDetailsForRes(farmondto.getResourceCropDTO().getResourceId(),
                        startDate, endDate);
        farmondto.setRescroplist(rescroplist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("resCropSumHarDt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getRescropSumPerHarDt(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        Date startDate;
        Date endDate;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        startDate = formatter.parse(farmondto.getReportstartdt());
        endDate = formatter.parse(farmondto.getReportenddt());
        List<ResourceCropDTO> rescroplist = masterDataService
                .getSummaryPerResForHrvst(startDate, endDate,
                        farmondto.getResourceCropDTO().getHarvestId());
        farmondto.setRescroplist(rescroplist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("resCropPerHarDt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getRescropPerPerHarDt(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        Date startDate;
        Date endDate;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        startDate = formatter.parse(farmondto.getReportstartdt());
        endDate = formatter.parse(farmondto.getReportenddt());
        List<ResourceCropDTO> rescroplist = masterDataService
                .getRescropDetails(farmondto.getResourceCropDTO().getHarvestId(),
                        startDate, endDate);
        farmondto.setRescroplist(rescroplist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("labCropSumHarDt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getLabcropSumPerHarDt(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        Date startDate;
        Date endDate;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        startDate = formatter.parse(farmondto.getReportstartdt());
        endDate = formatter.parse(farmondto.getReportenddt());
        LabourCropDTO labcropsummary = masterDataService
                .getTotalLabcropReport(farmondto.getLabcroprecord().getHarvestId(),
                        startDate, endDate);
        farmondto.setLabcroprecord(labcropsummary);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("labCropDtlsHarDt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getLabcropDtlsHarDt(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        Date startDate;
        Date endDate;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        startDate = formatter.parse(farmondto.getReportstartdt());
        endDate = formatter.parse(farmondto.getReportenddt());
        List<LabourCropDTO> labcropdetails = masterDataService
                .getLabcropDetails(farmondto.getLabcroprecord().getHarvestId(),
                        startDate, endDate);
        farmondto.setLabcroplist(labcropdetails);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("empExpensePerDt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpExpensePerDt(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        Date startDate;
        Date endDate;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        startDate = formatter.parse(farmondto.getReportstartdt());
        endDate = formatter.parse(farmondto.getReportenddt());
        List<ExpenseDTO> expenselist = masterDataService
                .getAllEmpExpenseInRange(startDate, endDate);
        farmondto.setExpenselist(expenselist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("empNameForId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpNameForId(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        EmployeeDTO emprec = masterDataService
                .getEmpNameForId(farmondto.getEmprec().getId());
        farmondto.setEmprec(emprec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("activeEmpExp")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getActiveEmpExpense(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        EmpExpDTO activerexmpexp = masterDataService.getEmpActiveExpRec(farmondto
                .getEmpexprec().getEmpid(), farmondto.getEmpexprec().getExpcategory());
        farmondto.setEmpexprec(activerexmpexp);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("editEmpExp")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editEmpExpense(String termDTOJSON) throws NamingException{
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        
        MasterDataServices masterDataService = new MasterDataServices();
        int editres = masterDataService.editEmpExpRecord(farmondto.getEmpexprec());
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
    
    @Path("maxEmpExpId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxEmpExpId(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        String maxid = String.valueOf(masterDataService.getMaxEmpExpenseId());
        EmpExpDTO empexprec = new EmpExpDTO();
        empexprec.setId(maxid);
        farmondto.setEmpexprec(empexprec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("addEmpExp")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addEmpExpeRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int addres = masterDataService.addEmpExpRecord(farmondto.getEmpexprec());
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
    
    @Path("empLeaves")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpLeaveCount(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        int leavecount = masterDataService.getCountLeaveEmp(farmondto.getEmpleaverec()
                .getEmpid());
        EmpLeaveDTO leaverec = new EmpLeaveDTO();
        leaverec.setId(String.valueOf(leavecount));
        farmondto.setEmpleaverec(leaverec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("maxEmpLeave")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxEmpLeave(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        String maxid = String.valueOf(masterDataService.getMaxEmpLeaveId());
        EmpLeaveDTO empleaverec = new EmpLeaveDTO();
        empleaverec.setId(maxid);
        farmondto.setEmpleaverec(empleaverec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("addEmpLeave")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addEmpLeave(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int addres = masterDataService.addEmpleaveRecord(farmondto.getEmpleaverec());
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
    
    @Path("empActiveLoans")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getActiveEmpLoans(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        List<EmpExpDTO> activeloans = masterDataService.getEmpActiveLoans();
        farmondto.setEmpexplist(activeloans);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("empPaybkPerLoan")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpPaybkPerLoan(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();

        List<EmpExpDTO> paybacklist = masterDataService
                .getPaybackList(farmondto.getEmpexprec().getEmpid(),
                        farmondto.getEmpexprec().getId());
        farmondto.setEmpexplist(paybacklist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("maxEmpId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxEmpId(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        String maxid = String.valueOf(masterDataService.getMaxEmployeeId());
        EmployeeDTO emprec = new EmployeeDTO();
        emprec.setId(maxid);
        farmondto.setEmprec(emprec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Path("getActiveEmp")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getActiveEmpRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        List<EmployeeDTO>activeemplist = masterDataService.getActiveEmployeeList();
        
        farmondto.setEmplist(activeemplist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
//    @Path("empNameforId")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getEmpNameforId(String termDTOJSON) throws NamingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        FarmonDTO farmondto;
//        try {
//            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
//            farmondto = (FarmonDTO) DTO;
//        } catch (IOException ex) {
//            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
//            farmondto = new FarmonDTO();
//            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
////            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
//        }
//        MasterDataServices masterDataService = new MasterDataServices();
//        EmployeeDTO emprec = masterDataService.getEmpNameForId(farmondto.getEmprec().getId()); 
//        
//        farmondto.setEmprec(emprec);
//        try {
//            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
//            return responseTermDTOJSON;
//        } catch (JsonProcessingException ex) {
//            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
    
    @Path("addEmployee")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addEmployeeRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int addres = masterDataService.addEmployeeRecord(farmondto.getEmprec());
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

    @Path("editEmployee")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editEmployee(String termDTOJSON) throws NamingException{
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        
        MasterDataServices masterDataService = new MasterDataServices();
        int editres = masterDataService.editEmployeeRecord(farmondto.getEmprec());
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
    
    @Path("maxResCropId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxResCropId(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        String maxid = String.valueOf(masterDataService.getMaxIdForResCrop());
        ResourceCropDTO rescroprec = new ResourceCropDTO();
        rescroprec.setApplicationId(maxid);
        farmondto.setResourceCropDTO(rescroprec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("resCropExpMonthly")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResCropExpPerMonth(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
//        LocalDate today = LocalDate.now();
//
//        // Get the first day of previous month
//        LocalDate firstDayOfPrevMonth = today.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
//
//        // Get the last day of previous month
//        LocalDate lastDayOfPrevMonth = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        String firstDayFormatted = firstDayOfPrevMonth.format(formatter);
//        String lastDayFormatted = lastDayOfPrevMonth.format(formatter);
        String firstDayFormatted = farmondto.getReportstartdt();
        String lastDayFormatted = farmondto.getReportenddt();
        MasterDataServices masterDataService = new MasterDataServices();
        List<AllExpenseReportDTO> expenselist = masterDataService.getRescropExpRpt(firstDayFormatted,
                lastDayFormatted);
        farmondto.setAllexplist(expenselist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("resAcqReport")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResAcqReport(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }

        MasterDataServices masterDataService = new MasterDataServices();
        List<ResAcqReportDTO> resacqreport = masterDataService.getAcqResources();
        farmondto.setResacqreport(resacqreport);
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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

    @Path("maxLabcropId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxLabcropId(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int maxid = masterDataService.getMaxIdForLabCrop();
        LabourCropDTO labcroprec = new LabourCropDTO();
        labcroprec.setApplicationId(String.valueOf(maxid));
        farmondto.setLabcroprecord(labcroprec);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("addLabcrop")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addLabcropRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int addres = masterDataService.addLabourCropRecord(farmondto.getLabcroprecord());
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

    @Path("delLabcrop")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delLabcropRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int delres = masterDataService.delLabourCropRecord(farmondto.getLabcroprecord());
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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

    @Path("resAcqResid")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getResacqForResid(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
        }
        MasterDataServices masterDataService = new MasterDataServices();
        ResAcquireDTO resacqrec = masterDataService.getResAcqPerResid(farmondto.
                getResacqrec().getResoureId());
        farmondto.setResacqrec(resacqrec);
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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
        } catch (IOException ex) {
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

    @Path("nonzeroStockList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getNonzeroShopresperResid(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
        }
        MasterDataServices masterDataService = new MasterDataServices();
        List<ShopResDTO> shopreslist = masterDataService.getShopResForResid(farmondto.
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
        } catch (IOException ex) {
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

    @Path("taskLstBtnDt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getTaskLstBtnDt(String termDTOJSON) throws NamingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        Date startDate;
        Date endDate;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        startDate = formatter.parse(farmondto.getReportstartdt());
        endDate = formatter.parse(farmondto.getReportenddt());
        List<TaskPlanDTO> taskplanlist = masterDataService
                .getTaskdDetailsBetweenDates(startDate, endDate);
        farmondto.setTaskplanlist(taskplanlist);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("maxTaskplanId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getMaxTaskplanId(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        String maxfarmresid = String.valueOf(masterDataService.getMaxTaskplanId());
        TaskPlanDTO taskplandto = new TaskPlanDTO();
        taskplandto.setTaskId(maxfarmresid);
        farmondto.setTaskplanrec(taskplandto);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("addTaskplan")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addTaskRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int addtaskplan = masterDataService.addTaskplanRecord(farmondto.getTaskplanrec());
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_ADD_RES(addtaskplan);
        farmondto.setResponses(farmonres);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("editTaskplan")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editTaskRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int editaskplan = masterDataService.editTaskplanRecord(farmondto.getTaskplanrec());
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_EDIT_RES(editaskplan);
        farmondto.setResponses(farmonres);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("deleteTaskplan")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteTaskRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }

        MasterDataServices masterDataService = new MasterDataServices();
        int deleteaskplan = masterDataService.deleteTaskplanRecord(farmondto.getTaskplanrec());
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_DEL_RES(deleteaskplan);
        farmondto.setResponses(farmonres);

        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("addRescrop")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addRescropRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int addrecrop = masterDataService.addResCropRecord(farmondto.getResourceCropDTO());
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_ADD_RES(addrecrop);
        farmondto.setResponses(farmonres);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("delRescrop")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delResCropRec(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        int delrescrop = masterDataService.delResCropRecord(farmondto.getResourceCropDTO());
        FarmonResponse farmonres = new FarmonResponse();
        farmonres.setFarmon_DEL_RES(delrescrop);
        farmondto.setResponses(farmonres);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Path("taskplanPerId")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getTaskplanForId(String termDTOJSON) throws NamingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FarmonDTO farmondto;
        try {
            Object DTO = objectMapper.readValue(termDTOJSON, FarmonDTO.class);
            farmondto = (FarmonDTO) DTO;
        } catch (IOException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            farmondto = new FarmonDTO();
            farmondto.getUserDto().setResponseMsg("JSON_FORMAT_PROBLEM");
//            userdto.setResponseCode(HedwigResponseCode.JSON_FORMAT_PROBLEM);            
        }
        MasterDataServices masterDataService = new MasterDataServices();
        TaskPlanDTO taskplandto = masterDataService.getTaskPlanForId(farmondto.
                getTaskplanrec().getTaskId());
        farmondto.setTaskplanrec(taskplandto);
        try {
            String responseTermDTOJSON = objectMapper.writeValueAsString(farmondto);
            return responseTermDTOJSON;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
