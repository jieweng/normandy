package com.pearl.normandy.customer;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CustomerDao extends SqlMapClientDaoSupport {

	//==============================
	//Get methods
	//==============================
	@SuppressWarnings("unchecked")
	public List<CustomerTo> getAllCustomer() throws DataAccessException{
		List<CustomerTo> result = getSqlMapClientTemplate().queryForList("Customer.selectAllCustomer");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public CustomerTo getCustomerByName(String customerName) throws DataAccessException{
		List<CustomerTo> result = getSqlMapClientTemplate().queryForList("Customer.selectCustomerByName", customerName);
		if(result.size() > 0){
			return result.get(0);
		}
		else{
			return null;
		}
	}	
	
	//==============================
	//Create, Update, Delete
	//==============================	
	public CustomerTo create(CustomerTo customerTo) throws DataAccessException {
		Integer id = (Integer)this.getSqlMapClientTemplate().insert("Customer.insert", customerTo);
		customerTo.setId(id);
		return customerTo;
	}	
}