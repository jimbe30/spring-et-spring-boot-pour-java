package com.mycompany.invoise.repository;

import com.mycompany.invoise.entity.Invoice;

public class InvoiceRepositoryDatabase implements InvoiceRepositoryInterface {
	
	
	public void create(Invoice invoice) {

		System.out.println("Invoice num�ro " + invoice.getNumber() + " ajout�e en BdD pour le client " + invoice.getCustomerName());
	}

}