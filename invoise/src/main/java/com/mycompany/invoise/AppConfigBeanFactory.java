package com.mycompany.invoise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mycompany.invoise.repository.InvoiceRepositoryDatabase;
import com.mycompany.invoise.repository.InvoiceRepositoryInterface;
import com.mycompany.invoise.service.InvoiceServiceInterface;
import com.mycompany.invoise.service.InvoiceServicePrefix;

@Configuration
public class AppConfigBeanFactory {
	@Bean
	InvoiceServiceInterface invoiceService() {
		return new InvoiceServicePrefix();
	}
	@Bean
	InvoiceRepositoryInterface invoiceRepository() {
		return new InvoiceRepositoryDatabase();
	}
}
