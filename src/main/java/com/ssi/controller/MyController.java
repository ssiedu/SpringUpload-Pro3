package com.ssi.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
	
	
	@RequestMapping("viewallcustomers")
	public ModelAndView showAllCustomers(){
		List<Customer> customers=dao.getAllCustomers();
		ModelAndView mv=new ModelAndView("customerlist");
		mv.addObject("customers", customers);
		return mv;
	}
	
	@RequestMapping("LoadIdentity")
	public void readIdentity(@RequestParam("code") int code, HttpServletResponse response){
		response.setContentType("application/pdf");
		//response.setContentType("application/msword");
		Customer customer=dao.searchCustomer(code);
		Blob blob=customer.getIdentity();
		byte b[]=null;
		try{
		b=blob.getBytes(1, (int)blob.length());
		ServletOutputStream out=response.getOutputStream();
		out.write(b);
		out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping("LoadImage")
	public void readImage(@RequestParam("code") int code, HttpServletResponse response){
		Customer customer=dao.searchCustomer(code);
		Blob blob=customer.getPicture();
		byte b[]=null;
		try{
		b=blob.getBytes(1, (int)blob.length());
		ServletOutputStream out=response.getOutputStream();
		out.write(b);
		out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("searchcustomer")
	public ModelAndView showSearchResult(@RequestParam("code") int code){
		Customer customer=dao.searchCustomer(code);
		ModelAndView mv=new ModelAndView("searchresult");
		mv.addObject("customer", customer);
		return mv;
	}
	@RequestMapping("search")
	public String showSearchForm(){
		return "searchform";
	}
	@RequestMapping("savecustomer")
	public ModelAndView saveCustomerData(@ModelAttribute("customer") Customer customer, @RequestParam("cpic") MultipartFile file1, @RequestParam("idproof") MultipartFile file2){
		
		try {
			byte b[]=file1.getBytes();
			Blob blob=BlobProxy.generateProxy(b);
			customer.setPicture(blob);
			
			byte idbytes[]=file2.getBytes();
			Blob idblob=BlobProxy.generateProxy(idbytes);
			customer.setIdentity(idblob);
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
