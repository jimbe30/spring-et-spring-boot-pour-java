package com.mycompany.invoise.repository;

import java.util.List;

import com.mycompany.invoise.entity.Invoice;

public interface InvoiceRepositoryInterface {
	
	void create(Invoice invoice);
	List<Invoice> list();
	Invoice getById(String invoiceNumber);

}
