package com.mtch.hmgmt.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@EnableAutoConfiguration (exclude=HibernateJpaAutoConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.mtch.hmgmt"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class HotelMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelMgmtApplication.class, args);
	}
}
