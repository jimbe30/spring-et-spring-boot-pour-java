package com.mycompany.invoise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mycompany.invoise.controller.InvoiceControllerInterface;

@SpringBootApplication
@EnableAutoConfiguration
public class App {

	public static void main(String[] args) {		
		ApplicationContext context = SpringApplication.run(App.class);
		InvoiceControllerInterface invoiceController = context.getBean(InvoiceControllerInterface.class);
		invoiceController.createInvoice();		
	}
}
