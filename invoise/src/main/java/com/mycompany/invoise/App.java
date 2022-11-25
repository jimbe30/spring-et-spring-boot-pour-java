package com.mycompany.invoise;

import java.util.Scanner;

import com.mycompany.invoise.controller.InvoiceController;
import com.mycompany.invoise.controller.InvoiceControllerMichel;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle est votre configuration (1 ou 2) ?");
		int configuration = sc.nextInt();
		if (configuration == 1) {
			InvoiceController invoiceController = new InvoiceController();
			invoiceController.createInvoiceUsingConsole();
		} else if (configuration == 2) {
			InvoiceControllerMichel invoiceController = new InvoiceControllerMichel();
			invoiceController.createInvoiceUsingWebForm();
		}
		sc.close();

	}

}
