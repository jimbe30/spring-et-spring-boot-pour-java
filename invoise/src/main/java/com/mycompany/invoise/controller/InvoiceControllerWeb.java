package com.mycompany.invoise.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.service.InvoiceServiceInterface;

public class InvoiceControllerWeb implements InvoiceControllerInterface {
	
	@Autowired
	InvoiceServiceInterface invoiceService;
	
	public InvoiceControllerWeb() {
		super();
	}

	public InvoiceControllerWeb(InvoiceServiceInterface invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	public void setInvoiceService(InvoiceServiceInterface invoiceService) {
		this.invoiceService = invoiceService;
	}

	public void createInvoice() {

		String customerName = "Tesla";
		Invoice invoice = new Invoice();
		invoice.setCustomerName(customerName);

		invoiceService.createInvoice(invoice);


	}

}
