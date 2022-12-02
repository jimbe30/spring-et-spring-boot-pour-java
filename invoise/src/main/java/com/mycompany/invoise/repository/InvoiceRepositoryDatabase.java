package com.mycompany.invoise.repository;

import java.util.Arrays;
import java.util.List;

import com.mycompany.invoise.entity.Invoice;

public class InvoiceRepositoryDatabase implements InvoiceRepositoryInterface {
	
	
	public void create(Invoice invoice) {

		System.out.println("Invoice numéro " + invoice.getNumber() + " ajoutée en BdD pour le client " + invoice.getCustomerName());
	}

	@Override
	public List<Invoice> list() {
		Invoice invoice1 = new Invoice();
		invoice1.setNumber("NUM_00001");
		invoice1.setCustomerName("United Federal Company");
		
		Invoice invoice2 = new Invoice();
		invoice2.setNumber("NUM_00002");
		invoice2.setCustomerName("Connor the Notorious");
		return Arrays.asList(
			invoice1,
			invoice2
		);
	}

}
