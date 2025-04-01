package com.sb.project;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.project.dto.*;
import com.sb.project.service.ScmNewItemService;
import com.sb.project.service.ScmNewItemServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	  @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private ScmNewItemServiceImpl service; // Replace with your actual service class
	    
	    ScmPcNewItemDTO dto=new ScmPcNewItemDTO();
	    
		ScmNewItemDTO mockDTO1=new ScmNewItemDTO();
		
		ScmNewItemDTO mockDTO=new ScmNewItemDTO();
		
		ScmPcNewItemDTO dto1=new ScmPcNewItemDTO();
		
		List<ScmNewItemDTO> mockDTOList=new ArrayList<>();
		
		List<ScmNewItemDTO> getList=getScmNewItemDTO();
    	List<ScmPcNewItemDTO> getListPC=new ArrayList<>();
    	
    	List<ItemsJoinDTO> getLists=new ArrayList<>();

	    @Test
	    void testSaveScmItems() throws Exception {
	        // Mock Input
	        ScmPcNewItemDTO mockDTO = new ScmPcNewItemDTO();
	      
	        // Set relevant fields
	       
	        // Mock Service Response
	        String mockResponse = "Item saved successfully";
	        Mockito.when(service.saveItems(Mockito.any(ScmPcNewItemDTO.class)))
	               .thenReturn(mockResponse);

	        // Perform Mock MVC Call
	        mockMvc.perform(post("/item/saveNewItem")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(mockDTO)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.status").value("Success"))
	                .andExpect(jsonPath("$.results").value(mockResponse));

	        // Verify service method was called
	        Mockito.verify(service, times(1)).saveItems(Mockito.any(ScmPcNewItemDTO.class));
	        
	       
	        	
	        
	    }
	    @Test
	    void testgetItemBypcValue() throws Exception {
	    	
	    	ScmPcNewItemDTO mockDTO = new ScmPcNewItemDTO();
	    	double pcValue=1550.75;
	    	
	            when(service.getByPcItemValue(pcValue)).thenReturn(mockDTO);
	            mockMvc.perform(get("/item/getItemByPc/{getPcValue}",pcValue).contentType(MediaType.APPLICATION_JSON)).
	            andExpect(status().isOk()).
	            andExpect(jsonPath("$.status").value("Success")).
	            andExpect(jsonPath("$.results").value(mockDTO));
	            
	            verify(service,times(1)).getByPcItemValue(pcValue);
	    	
	    }
	    @Test
	    void testgetAllIyemsBySuppCode() throws Exception {
	    	
	    	List<ItemsJoinDTO> getAllItems=new ArrayList<>();
	    	
	    	ItemsJoinDTO dto=new ItemsJoinDTO();
	    	dto.setPcItemCreatedBy("teja");
	    	dto.setPcItemRemarks("good");
	    	dto.setPciItemQuantity(300.00);
	    	dto.setPciItemUnits("10");
	    	getAllItems.add(dto);
	    	ItemsJoinDTO dto1=new ItemsJoinDTO();
	    	dto1.setPcItemCreatedBy("naga");
	    	dto1.setPcItemRemarks("good");
	    	dto1.setPciItemQuantity(500.00);
	    	dto1.setPciItemUnits("20");
	    	getAllItems.add(dto1);
	    	
	    	String suppCode="SUP123";
	    	when(service.getItemsBySupCode(suppCode)).thenReturn(getAllItems);
	    	mockMvc.perform(get("/item/getItemBySuppCode/{suppcode}",suppCode).contentType(MediaType.APPLICATION_JSON))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.status").value("Success"))
	    	.andExpect(jsonPath("$.results[0].pcItemRemarks").value("good"))
            .andExpect(jsonPath("$.results[0].pcItemCreatedBy").value("teja"))
            .andExpect(jsonPath("$.results[0].pciItemQuantity").value(300.0))
            .andExpect(jsonPath("$.results[0].pciItemUnits").value("10"))
            .andExpect(jsonPath("$.results[1].pcItemRemarks").value("good"))
            .andExpect(jsonPath("$.results[1].pcItemCreatedBy").value("naga"))
            .andExpect(jsonPath("$.results[1].pciItemQuantity").value(500.0))
            .andExpect(jsonPath("$.results[1].pciItemUnits").value("20"));
	    	verify(service,times(1)).getItemsBySupCode(suppCode);
	    	
	    }
	    
	    public List<ScmPcNewItemDTO> getScmNewPcItemDTO() {
	    	
	    	
	    	dto.setSuppCode("SUP123");
	    	dto.setPcItemValue(1500.75);
	    	dto.setPcItemRemarks("Sample item remarks.");
	    	dto.setPcItemStatus(1);
	    	dto.setPcItemCreatedBy("Admin");
	    	dto.setPcItemCreatedDate(LocalDate.now());
	    	dto.setPcItemModifiedBy("User1");
	    	dto.setPcItemModifiedDate(LocalDate.now());
	    	dto.setApprovedStatus(1);
	    	dto.setApprovedBy("Manager");
	    	dto.setApprovedDate(LocalDate.now());
	    	dto.setNewItems(getList);
	    	getListPC.add(dto);
	    	
	    	dto1.setSuppCode("SUP123");
	    	dto1.setPcItemValue(1500.75);
	    	dto1.setPcItemRemarks("Sample item remarks.");
	    	dto1.setPcItemStatus(1);
	    	dto1.setPcItemCreatedBy("Admin");
	    	dto1.setPcItemCreatedDate(LocalDate.now());
	    	dto1.setPcItemModifiedBy("User1");
	    	dto1.setPcItemModifiedDate(LocalDate.now());
	    	dto1.setApprovedStatus(1);
	    	dto1.setApprovedBy("Manager");
	    	dto1.setApprovedDate(LocalDate.now());
	    	dto1.setNewItems(getList);    	
	    	getListPC.add(dto1);
			return getListPC;
	    	
	    }
	    
	     
	    public List<ScmNewItemDTO> getScmNewItemDTO() {
	    	
	    
	    	
	    
	    	mockDTO.setPciItemName("Test Item");
	    	mockDTO.setPciItemDesc("This is a test item description.");
	    	mockDTO.setPciItemQuantity(10.5);
	    	mockDTO.setPciItemUnits("KG");
	    	mockDTO.setPciStatus(1);
	    	mockDTO.setPciCreatedBy("Admin");
	    	mockDTO.setPciCreatedDate(LocalDateTime.now());
	    	mockDTO.setPciModifiedBy("teja");
	    	mockDTO.setPciModifiedDate(LocalDateTime.now());
	    	mockDTO.setPciMake("TestMake");
	    	mockDTO.setPciModel("Model123");
	    	mockDTO.setPciItemDeliveryDate("2025-02-10");
	   
	    	mockDTO1.setPciItemName("Test Item");
	    	mockDTO1.setPciItemDesc("This is a test item description.");
	    	mockDTO1.setPciItemQuantity(10.5);
	    	mockDTO1.setPciItemUnits("KG");
	    	mockDTO1.setPciStatus(1);
	    	mockDTO1.setPciCreatedBy("Admin");
	    	mockDTO1.setPciCreatedDate(LocalDateTime.now());
	    	mockDTO1.setPciModifiedBy("teja");
	    	mockDTO1.setPciModifiedDate(LocalDateTime.now());
	    	mockDTO1.setPciMake("TestMake");
	    	mockDTO1.setPciModel("Model123");
	    	mockDTO1.setPciItemDeliveryDate("2025-02-10");
	    	mockDTOList.add(mockDTO);
	    	mockDTOList.add(mockDTO1);
			return mockDTOList;

        	
        }
	    public List<ItemsJoinDTO> getItemsDto(){
	    	       getListPC=getScmNewPcItemDTO();
	            getLists=getList.stream().map(list->{
	    		ItemsJoinDTO item=new ItemsJoinDTO();
	    		ScmPcNewItemDTO getPc=new ScmPcNewItemDTO();
	    		ScmNewItemDTO getItem=new ScmNewItemDTO();
	    		item.setPciItemQuantity(getItem.getPciItemQuantity());
	    		item.setPciItemUnits(getItem.getPciItemUnits());
	    		item.setPcItemCreatedBy(getPc.getPcItemCreatedBy());
	    		item.setPcItemRemarks(getPc.getPcItemRemarks());
	    		return item;
	    		
	    	}).collect(Collectors.toList());
			return getLists;
	    	
	    	
	    }

}
