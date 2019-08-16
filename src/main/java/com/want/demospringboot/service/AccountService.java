package com.want.demospringboot.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.want.demospringboot.dto.AccountDTO;

@Service
public interface AccountService {
	
	public Iterable<AccountDTO> list(Pageable pageable);
	
	public Boolean insert(AccountDTO account);
	
	public void delete(Long id);
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signUp(String username, String password, String emailAddress);

	@SuppressWarnings("rawtypes")
	public ResponseEntity signIn(String username, String password);
	
	public String queryUserInfo(HttpServletRequest request);
	
}
