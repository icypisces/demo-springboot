package com.want.demospringboot.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.want.demospringboot.dto.AccountDTO;
import com.want.demospringboot.dto.LoginUserInfo;

@Service
public interface AccountService {
	
	public Iterable<AccountDTO> list(Pageable pageable);
	
	public Boolean insert(AccountDTO account);
	
	public void delete(Long id);
	
	public LoginUserInfo signIn(String username, String password);
	
	public AccountDTO queryUserInfo(Long id);
	
	public Boolean checkUsernameExist(String username);

}
