package com.ssi.controller;

import java.io.IOException;
import java.sql.Blob;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssi.dao.CustomerDAO;
import com.ssi.entity.Customer;

@Controller
public class MyController {
	
	@Autowired
	private CustomerDAO dao;
	
	@RequestMapping("savecustomer")
	public ModelAndView saveCustomerData(@ModelAttribute("customer") Customer customer, @RequestParam("cpic") MultipartFile file){
		
		try {
			byte b[]=file.getBytes();
			Blob blob=BlobProxy.generateProxy(b);
			customer.setPicture(blob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.saveCustomer(customer);
		ModelAndView mv=new ModelAndView("saveconfirm");
		return mv;
	}
	
	@RequestMapping("newcustomer")
	public String showDataEntryForm(){
		return "dataentry";
	}
}
