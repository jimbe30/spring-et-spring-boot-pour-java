package com.mycompany.invoise.service;

import java.util.List;

import com.mycompany.invoise.entity.Invoice;

public interface InvoiceServiceInterface {

	
	void createInvoice(Invoice invoice);
	List<Invoice> getInvoiceList();
}
