package com.mycompany.invoise.repository;

import com.mycompany.invoise.entity.Invoice;

public class InvoiceRepositoryMichel implements InvoiceRepositoryInterface {
	
	
	public void create(Invoice invoice) {

		System.out.println("Invoice num�ro " + invoice.getNumber() + " ajout�e en BdD pour le client " + invoice.getCustomerName());
	}

}
