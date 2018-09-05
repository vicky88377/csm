package com.mindtree.customerservicemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.customerservicemanagement.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT * FROM customer_details WHERE email_ID=?", nativeQuery = true)
	Customer findCustomeremail(String email_id);
	
	@Query(value = "SELECT * FROM customer_details WHERE email_ID=?", nativeQuery = true)
	Customer getIdFromEmail(String email_id);
	
	@Modifying
	@Transactional
	@Query(value = "delete from customer_details WHERE email_ID=?", nativeQuery = true)
	int deleteByMailId(String email_id);
	
	@Modifying
	@Transactional
	@Query(value = "update customer_details set customer_name=?1 ,contact_number = ?2, area = ?3,address_Line_1 = ?4,address_Line_2 = ?5,pin_code = ?6 WHERE email_ID=?7", nativeQuery = true)
	int updateCustomerDetails(String name, String contact,
			String area, String add1, String add2, int pin,String tokenEmail);

	@Query(value = "SELECT customer_name FROM customer_details WHERE email_ID=?", nativeQuery = true)
	String findCustomeremail1(String email_id);

}
