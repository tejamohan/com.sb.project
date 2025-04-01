package com.sb.project.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.netflix.discovery.converters.Auto;
import com.sb.project.dto.ItemDetailsDTO;
import com.sb.project.dto.ItemsJoinDTO;
import com.sb.project.dto.ScmPcNewItemDTO;
import com.sb.project.dto.SuppcodeProcdureCallDTO;
import com.sb.project.dto.UserResponse;
import com.sb.project.entity.ItemsEntity;
import com.sb.project.exception.ApiResponse;
import com.sb.project.exception.ItemNotFoundException;
import com.sb.project.service.ScmNewItemServiceImpl;
//import com.teja.payment.beans.UserResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

//import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/item")
public class ScmNewItemController {
	
	Logger log=LoggerFactory.getLogger(ScmNewItemController.class);
	
	private int attempt=1;
	
	//public static final String USER_DATA="userService";
	
	@Autowired
	private ScmNewItemServiceImpl service;
	
	@Autowired
	private RestTemplate template;
	
	@PostMapping("/saveNewItem")
	public ResponseEntity<ApiResponse<?>> saveScmItems(@RequestBody ScmPcNewItemDTO itemsDTO) throws ItemNotFoundException{
		
		String response=service.saveItems(itemsDTO);
		
		ApiResponse<?> response1=ApiResponse.
                builder().
                status("Success").
                results(response).
                build();
		
		
		return new ResponseEntity<>(response1,HttpStatus.CREATED);
		
	}
	//@CircuitBreaker(name = "USER_DATA",fallbackMethod = "findAllUsers")
		//@Retry(name = "USER_DATA",fallbackMethod = "findAllUsers")
	
	@GetMapping("/findUsers")
	@CircuitBreaker(name = "userService",fallbackMethod = "findAllUsers")
	public List<ItemsEntity> displayUsers() {

		 RestTemplate restTemplate = new RestTemplate();

	        // Define API URL with path variables
	      
	        String url = "http://localhost:2929/getItemByDate/2024-08-20/2024-08-20";

	        // Define request headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

	        // Create request entity
	        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

	        // Define response type
	        ParameterizedTypeReference<List<ItemsEntity>> responseType =
	                new ParameterizedTypeReference<List<ItemsEntity>>() {};

	        // Make GET request
	        ResponseEntity<List<ItemsEntity>> response =
	                restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);

	        // Process response
	        if (response.getStatusCode() == HttpStatus.OK) {
	            List<ItemsEntity> items = response.getBody();
	            items.forEach(System.out::println);
	        } else {
	            System.out.println("Request failed with status: " + response.getStatusCode());
	        }
			return response.getBody();
	    }
	


	
	
	public List<UserResponse> findAllUsers(Exception e){
		
		log.info("response getting from fallback findAllUsers()");
		String url="https://jsonplaceholder.typicode.com/users";
		List<UserResponse> response=template.getForObject(url, List.class);
		log.info("response getting from fallback findAllUsers()");
		return response;
		
	}
	
	@GetMapping("/getItemByPc/{getPcValue}")
	public ResponseEntity<ApiResponse<?>> getItemBypcValue(@PathVariable Double getPcValue) throws ItemNotFoundException{
		ScmPcNewItemDTO response=service.getByPcItemValue(getPcValue);
		ApiResponse<?> customResponse=ApiResponse.builder().status("Success").results(response).build();
		
		return new ResponseEntity<>(customResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/getItemByStatus/{status}")
	public ResponseEntity<ApiResponse<?>> getItemBypcValue(@PathVariable Integer status) throws ItemNotFoundException{
		List<ItemDetailsDTO> response=service.getItemsByStatus(status);
		ApiResponse<?> customResponse=ApiResponse.builder().status("Success").results(response).build();
		
		return new ResponseEntity<>(customResponse,HttpStatus.OK);
		
	}
	
	@PostMapping("/getItemsProCall")
	public ResponseEntity<ApiResponse<?>> getItemsByProcedureCall(@RequestBody SuppcodeProcdureCallDTO suppDTO){
		List<Object[]> getResponse=service.getItemsBySuppProcedureCall(suppDTO);
		ApiResponse<?> customResponse=ApiResponse.builder().
				                                  status("Success").
				                                  results(getResponse).
				                                  build();
		return new ResponseEntity<>(customResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllItems/{field}")
	public ResponseEntity<ApiResponse<?>> getAllItemsByFieldDesc(@PathVariable String field){
		    List<ScmPcNewItemDTO> getAllItems=service.getAllItemsByDescOrder(field);
		    ApiResponse<?> customResponse=ApiResponse.builder().status("Success").results(getAllItems).build();
		    
		  return new ResponseEntity<>(customResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/getItemBySuppCode/{suppcode}")
	public ResponseEntity<ApiResponse<?>> getItemBypcValue(@PathVariable String suppcode) throws ItemNotFoundException{
		List<ItemsJoinDTO> response=service.getItemsBySupCode(suppcode);
		ApiResponse<?> customResponse=ApiResponse.builder().status("Success").results(response).build();
		
		return new ResponseEntity<>(customResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllItemsByPagi/{offset}/{size}")
	public ResponseEntity<ApiResponse<?>> getAllItemsByPagination(@PathVariable Integer offset,@PathVariable Integer size ){
		    List<ScmPcNewItemDTO> getAllItems=service.getListByPagination(offset,size);
		    ApiResponse<?> customResponse=ApiResponse.builder().status("Success").results(getAllItems).build();
		    
		  return new ResponseEntity<>(customResponse,HttpStatus.OK);
	}
	
	@PatchMapping("/updatePcValue/{id}")
	public ResponseEntity<ApiResponse<?>> updatePcValue(@PathVariable Long id,@RequestBody ScmPcNewItemDTO pcValue) throws ItemNotFoundException{
		
		String result=service.updateDatePcValue(id, pcValue);
		ApiResponse<?> customResponse=ApiResponse.builder().status("Success").results(result).build();
		return new ResponseEntity<>(customResponse,HttpStatus.OK);
		
	}
	
	
	

}
