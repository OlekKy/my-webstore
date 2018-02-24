package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	private List<Customer> listOfCustomers = new ArrayList<Customer>();
	
	public InMemoryCustomerRepository() {
		
		Customer user1 = new Customer ("1001","Zakupujacy48","Warszawa");
		user1.setNoOfOrdersMade(4);
		
		Customer user2 = new Customer ("1002","Buyer72","Gdansk");
		user2.setNoOfOrdersMade(0);
		
		Customer user3 = new Customer ("1003","KupieOpla12","Poznan");
		user3.setNoOfOrdersMade(2);
		
		Customer user4 = new Customer ("1004","Bogaty8","Katowice");
		user4.setNoOfOrdersMade(3);
		
		Customer user5 = new Customer ("1005","Zebra23","Gliwice");
		user5.setNoOfOrdersMade(5);
		
		listOfCustomers.add(user1);
		listOfCustomers.add(user2);
		listOfCustomers.add(user3);
		listOfCustomers.add(user4);
		listOfCustomers.add(user5);

	}
	
	public List<Customer> getAllCustomers(){
		return listOfCustomers;
	}
}
