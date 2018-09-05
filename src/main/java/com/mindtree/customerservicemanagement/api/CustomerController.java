package com.mindtree.customerservicemanagement.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuthException;
import com.mindtree.customerservicemanagement.FireBaseAuthHelper;
import com.mindtree.customerservicemanagement.model.Customer;
import com.mindtree.customerservicemanagement.service.CustomerService;
import com.mindtree.customerservicemanagment.exception.CustomerNotFoundException;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

		  
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> addCustomer(@RequestHeader("Authorization") String token) throws CustomerNotFoundException, InterruptedException,CustomerNotFoundException, FirebaseAuthException, ExecutionException {
		LOGGER.info("Inside addCustomer Controller method");
		Map<String, String> map = new HashMap<String, String>();
		map = FireBaseAuthHelper.getUserInfo(token);
		Customer customer= new Customer();
		if(customerService.GetDetailsFromEmail(map.get("email")) == null){
			map = FireBaseAuthHelper.getUserInfo(token);					
			Customer cust = customerService.createCustomer(customer, map.get("email"), map.get("name"));
			return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);	
		}else{
			throw new CustomerNotFoundException("Email id already exist ");
		}		
		
	}	
	

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> updateCustomer(@RequestBody Customer customer,@RequestHeader("Authorization") String token) throws CustomerNotFoundException, InterruptedException, FirebaseAuthException, ExecutionException {
		LOGGER.info("Inside updateCustomer Controller method");		
		Map<String, String> map = new HashMap<String, String>();
		if (customer == null) {
			throw new CustomerNotFoundException("Not able to update the details");
		}
		map = FireBaseAuthHelper.getUserInfo(token);	
        customerService.updateCustomer(customer, map.get("email")); 		
 		return new ResponseEntity<Integer>(HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String token) throws CustomerNotFoundException, InterruptedException, FirebaseAuthException, ExecutionException {
		LOGGER.info("Inside deleteCustomer Controller method");
		Map<String, String> map = new HashMap<String, String>();
		map = FireBaseAuthHelper.getUserInfo(token);	
		if (customerService.getIdFromEmail(map.get("email")) == null)	
			return new ResponseEntity<String>("Customer is not registered",HttpStatus.PRECONDITION_FAILED);
		customerService.deleteCustomer(map.get("email"));		
		return new ResponseEntity<String>("Customer is deleted Successfully",HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomerdetails(
			@RequestHeader("Authorization") String token) throws InterruptedException, FirebaseAuthException, ExecutionException, CustomerNotFoundException {
		LOGGER.info("Inside getCustomer Controller method");	
		Map<String, String> map = new HashMap<String, String>();
		map = FireBaseAuthHelper.getUserInfo(token);
		if (customerService.getIdFromEmail(map.get("email")) == null)
			addCustomer(token);
			map = FireBaseAuthHelper.getUserInfo(token);
			return new ResponseEntity<Customer>(customerService.GetDetailsFromEmail(map.get("email")), HttpStatus.OK);
	}
	
}
