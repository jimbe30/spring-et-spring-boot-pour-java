package com.mycompany.invoise.service;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.repository.InvoiceRepositoryInterface;

public class InvoiceService implements InvoiceServiceInterface {
	
	private static long lastNumber;
	
	private InvoiceRepositoryInterface invoiceRepository;
	
	public InvoiceService(InvoiceRepositoryInterface invoiceRepository) {
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
