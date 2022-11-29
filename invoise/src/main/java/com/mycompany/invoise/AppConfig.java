package com.mycompany.invoise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mycompany.invoise.controller.InvoiceControllerInterface;
import com.mycompany.invoise.controller.InvoiceControllerWeb;
import com.mycompany.invoise.repository.InvoiceRepositoryDatabase;
import com.mycompany.invoise.repository.InvoiceRepositoryInterface;
import com.mycompany.invoise.service.InvoiceServiceInterface;
import com.mycompany.invoise.service.InvoiceServicePrefix;

@Configuration
@ComponentScan("com.mycompany.invoise")
@PropertySource("classpath:application.properties")
public class AppConfig {
	
	@Bean
	InvoiceControllerInterface invoiceController() {
		return new InvoiceControllerWeb();
	}
	
	@Bean
	InvoiceServiceInterface invoiceService() {
		return new InvoiceServicePrefix();
	}
	
	@Bean
	InvoiceRepositoryInterface invoiceRepository() {
		return new InvoiceRepositoryDatabase();
	}

}
