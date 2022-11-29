package com.mycompany.invoise;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.invoise.controller.InvoiceControllerInterface;

public class App {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		InvoiceControllerInterface invoiceController = context.getBean(InvoiceControllerInterface.class);
		invoiceController.createInvoice();
		

	}
 
}
