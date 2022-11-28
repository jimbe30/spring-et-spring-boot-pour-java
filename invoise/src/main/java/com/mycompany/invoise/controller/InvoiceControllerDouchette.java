package com.mycompany.invoise.controller;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.service.InvoiceServiceInterface;

public class InvoiceControllerDouchette implements InvoiceControllerInterface {
	
	InvoiceServiceInterface invoiceService;
	
	public InvoiceControllerDouchette() {
		super();
	}

	public InvoiceControllerDouchette(InvoiceServiceInterface invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	public void setInvoiceService(InvoiceServiceInterface invoiceService) {
		this.invoiceService = invoiceService;
	}

	public void createInvoice() {

		String customerName = "Magic knighte";
		System.out.println("Le client scanné par la douchette s'appelle " + customerName);

		Invoice invoice = new Invoice();
		invoice.setCustomerName(customerName);

		invoiceService.createInvoice(invoice);


	}

}
