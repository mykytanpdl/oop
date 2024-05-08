package com.banknew.business;

import com.banknew.business.account.AccountService;
import com.banknew.business.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bank2Application {
	private final AccountService accountService;
	private final TransactionService transactionService;

	@Autowired
	public Bank2Application(AccountService accountService, TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Bank2Application.class, args);
	}

}
