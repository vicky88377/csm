package com.mindtree.customerservicemanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.sym.Name;

@Entity
@Table(name = "customer_details")
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_ID")
	private int customer_id;

	@Column(name="customer_name")
	private String customer_name;

	@Column(name="contact_number")
	private String customer_contact_number;

	@Column(name="email_ID")
	private String email_id;

	@Column(name="area")
	private String area;

	@Column(name="address_Line_1")
	private String address1;

	@Column(name="address_Line_2")
	private String address2;

	@Column(name="pin_code")
	private int customer_pincode;

	public Customer(int customer_id, String customer_name,
			String customer_contact_number, String email_id, String area,
			String address1, String address2, int customer_pincode) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_contact_number = customer_contact_number;
		this.email_id = email_id;
		this.area = area;
		this.address1 = address1;
		this.address2 = address2;
		this.customer_pincode = customer_pincode;
	}

	public Customer() {
		super();
		
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_contact_number() {
		return customer_contact_number;
	}

	public void setCustomer_contact_number(String customer_contact_number) {
		this.customer_contact_number = customer_contact_number;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getCustomer_pincode() {
		return customer_pincode;
	}

	public void setCustomer_pincode(int customer_pincode) {
		this.customer_pincode = customer_pincode;
	}

}
