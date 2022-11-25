package com.mycompany.invoise;

import java.util.Scanner;

import com.mycompany.invoise.controller.InvoiceController;
import com.mycompany.invoise.controller.InvoiceControllerChamboule;
import com.mycompany.invoise.controller.InvoiceControllerInterface;
import com.mycompany.invoise.controller.InvoiceControllerMichel;
import com.mycompany.invoise.repository.InvoiceRepository;
import com.mycompany.invoise.repository.InvoiceRepositoryInterface;
import com.mycompany.invoise.repository.InvoiceRepositoryMichel;
import com.mycompany.invoise.service.InvoiceService;
import com.mycompany.invoise.service.InvoiceServiceInterface;
import com.mycompany.invoise.service.InvoiceServiceMichel;

public class App {

	public static void main(String[] args) {
		
		InvoiceControllerInterface invoiceController = null;
		InvoiceServiceInterface invoiceService;
		InvoiceRepositoryInterface invoiceRepository;

		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle est votre configuration (1, 2, 3 ou 4) ?");
		int configuration = sc.nextInt();
		
		if (configuration == 1) {
			invoiceRepository = new InvoiceRepository();			
			invoiceService = new InvoiceService(invoiceRepository);
			invoiceController = new InvoiceController(invoiceService);
			
		} else if (configuration == 2) {
			invoiceRepository = new InvoiceRepositoryMichel();			
			invoiceService = new InvoiceServiceMichel(invoiceRepository);
			invoiceController = new InvoiceControllerMichel(invoiceService);
			
		} else if (configuration == 3) {
			invoiceRepository = new InvoiceRepositoryMichel();			
			invoiceService = new InvoiceServiceMichel(invoiceRepository);
			invoiceController = new InvoiceControllerChamboule(invoiceService);
			
		} else if (configuration == 4) {
			invoiceRepository = new InvoiceRepository();			
			invoiceService = new InvoiceServiceMichel(invoiceRepository);
			invoiceController = new InvoiceControllerChamboule(invoiceService);
		}
		
		if (invoiceController != null) {
			invoiceController.createInvoice();
		}
		
		sc.close();

	}
 
}
