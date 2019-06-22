package com.ssi.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssi.entity.Customer;

@Component
public class CustomerDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public List<Customer> getAllCustomers(){
		Session session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(Customer.class);
		List<Customer> customers=cr.list();
		session.close();
		return customers;
	}

	public Customer searchCustomer(int code){
		Session session=sessionFactory.openSession();
		Customer customer=session.get(Customer.class, code);
		session.close();
		return customer;
	}
	public void saveCustomer(Customer customer){
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(customer);
		tr.commit();
		session.close();
	}
}
