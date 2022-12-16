package com.mycompany.invoise.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.service.InvoiceServiceInterface;

@Controller
@RequestMapping("/invoice")
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
	public String displayHome(HttpServletRequest request) {
		System.out.println("Invocation de InvoiceControllerWeb.displayHome()");
		request.setAttribute("invoices", invoiceService.getInvoiceList());
		return "index";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		System.out.println("Invocation de InvoiceControllerWeb.index()");
		List<Invoice> invoiceList = invoiceService.getInvoiceList();
		model.addAttribute( "invoices", invoiceList);
		return "index";
	}
	
	@RequestMapping("/{id}")
	public ModelAndView displayInvoice(@PathVariable("id") String invoiceNumber) {
		System.out.println("Invocation de InvoiceControllerWeb.displayInvoice()");
		Invoice invoice = invoiceService.getInvoice(invoiceNumber);
		return new ModelAndView("invoice-details", "invoice", invoice);
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
