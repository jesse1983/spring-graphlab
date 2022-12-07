package com.jesse1983.graphqlab;

import com.jesse1983.graphqlab.domain.*;
import com.jesse1983.graphqlab.repository.CustomerRepository;
import com.jesse1983.graphqlab.repository.ItemRepository;
import com.jesse1983.graphqlab.repository.ProductRepository;
import com.jesse1983.graphqlab.repository.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Date;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class GraphqlMeetingApplication {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private ItemRepository itemRepository;

	public static void main(String[] args) {
		SpringApplication.run(GraphqlMeetingApplication.class, args);
	}

	@Bean
	public Filter OpenFilter() {
		return new OpenEntityManagerInViewFilter();
	}

	@PostConstruct
	public void init() {
		// Customers
		Customer luke = Customer.builder()
				.firstName("Luke")
				.lastName("Skywalker")
				.email("luke.skywalker@starwars.com")
				.type(CustomerType.PERSON)
				.build();
		Customer hans = Customer.builder()
				.firstName("Hans")
				.lastName("Solo")
				.email("hans.solo@starwars.com")
				.type(CustomerType.PERSON)
				.build();
		Customer rebels = Customer.builder()
				.firstName("The")
				.lastName("Rebels")
				.email("rebels@starwars.com")
				.type(CustomerType.COMPANY)
				.build();
		customerRepository.save(luke);
		customerRepository.save(hans);
		customerRepository.save(rebels);

		// Products
		Product tv = Product.builder()
			.title("TV QLED")
			.price(980.90)
			.stock(10)
			.build();
		Product monitor = Product.builder()
			.title("Monitor OLED Curved")
			.price(230.10)
			.stock(54)
			.build();
		productRepository.save(tv);
		productRepository.save(monitor);

		// Items
		Item item1 = Item.builder()
			.product(tv)
			.discount(0.0)
			.amount(1.0)
			.build();
		Item item2 = Item.builder()
			.product(monitor)
			.amount(2.0)
			.discount(0.05)
			.build();
		Item item3 = Item.builder()
			.product(monitor)
			.amount(3.0)
			.discount(0.05)
			.build();
		itemRepository.save(item1);
		itemRepository.save(item2);
		itemRepository.save(item3);

		// Sales
		ArrayList<Item> sale1Items = new ArrayList<Item>();
		sale1Items.add(item1);
		sale1Items.add(item2);
		Sale sale1 = Sale.builder()
			.identifier("001")
			.customer(luke)
			.date(Date.valueOf("2022-01-01"))
			.comments("A good choice")
			.discount(0.10)
			.itens(sale1Items)
		.build();
		saleRepository.save(sale1);

		ArrayList<Item> sale2Items = new ArrayList<Item>();
		sale2Items.add(item3);
		Sale sale2 = Sale.builder()
			.identifier("002")
			.customer(hans)
			.date(Date.valueOf("2022-02-01"))
			.comments("Best choice")
			.discount(0.30)
			.itens(sale2Items)
		.build();
		saleRepository.save(sale2);
	}
}
