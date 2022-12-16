package com.mycompany.invoise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.repository.InvoiceRepositoryInterface;

public class InvoiceServicePrefix implements InvoiceServiceInterface {
	
	@Value("${invoice.lastNumber}")
	private long lastNumber;
	
	@Value("${invoice.prefix}")
	private String prefix;
	
	@Autowired
	private InvoiceRepositoryInterface invoiceRepository;
	
	public InvoiceServicePrefix() {
		super();
	}
	
	public InvoiceServicePrefix(InvoiceRepositoryInterface invoiceRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
	}

	public void createInvoice(Invoice invoice) {
		invoice.setNumber(prefix + "_" + (++lastNumber));
		invoiceRepository.create(invoice);
	}

	public long getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(long lastNumber) {
		this.lastNumber = lastNumber;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setInvoiceRepository(InvoiceRepositoryInterface invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@Override
	public List<Invoice> getInvoiceList() {
		return invoiceRepository.list();
	}

	@Override
	public Invoice getInvoice(String invoiceNumber) {
		// TODO Auto-generated method stub
		return invoiceRepository.getById(invoiceNumber);
	}
}
