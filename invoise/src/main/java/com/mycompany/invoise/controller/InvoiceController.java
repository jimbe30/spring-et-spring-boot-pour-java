package com.mycompany.invoise.controller;

import java.util.Scanner;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.service.InvoiceServiceInterface;

public class InvoiceController implements InvoiceControllerInterface {
	
	private InvoiceServiceInterface invoiceService;


	public InvoiceController(InvoiceServiceInterface invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	public void setInvoiceService(InvoiceServiceInterface invoiceService) {
		this.invoiceService = invoiceService;
	}

	public void createInvoice() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Name of the custumer please ?");
		String customerName = sc.nextLine();
		Invoice invoice = new Invoice();
		invoice.setCustomerName(customerName);
		invoiceService.createInvoice(invoice);

		sc.close();

	}

}
