package com.pearl.normandy.customer;

import java.util.List;

import org.apache.log4j.Logger;

public class CustomerService {

	static Logger log = Logger.getLogger(CustomerService.class.getName());

	// ==============================
	// Get methods
	// ==============================
	public List<CustomerTo> getAllCustomer() throws Exception{
		return customerDao.getAllCustomer();
	}

	//==============================
	//Injected Variables
	//==============================
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

}
