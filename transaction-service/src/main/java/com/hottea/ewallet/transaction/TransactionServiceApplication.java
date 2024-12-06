package com.hottea.ewallet.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@SpringBootApplication(scanBasePackages = {
//		"com.hottea.ewallet.common.messages",
//		"com.hottea.ewallet.common.api.util"
//})
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}

}
