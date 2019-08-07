package com.want.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.demospringboot.dto.AccountDTO;
import com.want.demospringboot.serviceImpl.AccountServiceImpl;

@RestController
@RequestMapping("PracticeOneJPA")
public class PracticeOneJPAController {
	
	@Autowired
	private AccountServiceImpl accountService;
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@RequestMapping("/list")
	public ResponseEntity list() {
		return ResponseEntity.ok(accountService.list(new PageRequest(1,4,new Sort(Sort.Direction.ASC, "id"))));
	}
	
	@RequestMapping("/save")
	public Boolean save(AccountDTO account) {
		return accountService.insert(account);
	}
	
//	@RequestMapping("/delete")
//	public List<AccountDTO> delete(Long id) {
//		accountService.delete(id);
//		return accountService.list();
//	}
	

}
