package com.techwording.batch.lifecycle;

import org.springframework.batch.item.ItemProcessor;

import com.techwording.batch.model.Customer;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer item) throws Exception {

		if (item == null) {
			return null;
		}
		Customer customer = new Customer(item.getFirstName()
			.toUpperCase(), item.getLastName()
				.toUpperCase());
		customer.setId(item.getId());
		// TODO Auto-generated method stub
		return customer;
	}

}
