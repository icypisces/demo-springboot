package com.want.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.demospringboot.dto.AccountDTO;
import com.want.demospringboot.serviceImpl.AccountServiceImpl;

@RestController
@RequestMapping("PracticeOneJPA")
public class PracticeOneJPAController {
	
	@Autowired
	private AccountServiceImpl accountService;
	
	@RequestMapping("/list")
	public List<AccountDTO> list() {
		return accountService.list();
	}
	
	@RequestMapping("/save")
	public AccountDTO save(AccountDTO account) {
		return accountService.save(account);
	}
	
	@RequestMapping("/delete")
	public List<AccountDTO> delete(Long id) {
		accountService.delete(id);
		return accountService.list();
	}
	

}
