package com.mycompany.invoise;

import java.util.Scanner;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.service.InvoiceService;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Name of the custumer please ?");
		String customerName = sc.nextLine();
		Invoice invoice = new Invoice();
		invoice.setCustomerName(customerName);
		InvoiceService invoiceService = new InvoiceService();
		invoiceService.createInvoice(invoice);
		
		sc.close();

	}

}
