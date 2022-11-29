package com.mycompany.invoise.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.repository.InvoiceRepositoryInterface;

public class InvoiceServiceNumber implements InvoiceServiceInterface {
	
	private static long lastNumber;
	
	@Autowired
	private InvoiceRepositoryInterface invoiceRepository;
	
	public InvoiceServiceNumber() {
		super();
	}
	
	public InvoiceServiceNumber(InvoiceRepositoryInterface invoiceRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
	}

	public void createInvoice(Invoice invoice) {
		invoice.setNumber(String.valueOf(++lastNumber));
		invoiceRepository.create(invoice);
	}

	public void setInvoiceRepository(InvoiceRepositoryInterface invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}
}
