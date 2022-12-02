package com.mycompany.invoise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.service.InvoiceServiceInterface;

@Controller
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
	
	@RequestMapping("/home")
	public String displayHome() {
		System.out.println("Invocation de InvoiceControllerWeb.displayHome()");
		return "";
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
