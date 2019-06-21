package com.ssi.dao;



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
	
	public void saveCustomer(Customer customer){
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(customer);
		tr.commit();
		session.close();
	}
}
