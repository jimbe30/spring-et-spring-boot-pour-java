package com.mycompany.invoise;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mycompany.invoise.controller.InvoiceControllerInterface;

@Configuration
@ComponentScan("com.mycompany.invoise")
public class App {

	public static void main(String[] args) {		
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		InvoiceControllerInterface invoiceController = context.getBean(InvoiceControllerInterface.class);
		invoiceController.createInvoice();		
	}
}
