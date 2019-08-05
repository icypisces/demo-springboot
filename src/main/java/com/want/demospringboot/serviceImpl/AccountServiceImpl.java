package com.want.demospringboot.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.demospringboot.dao.AccountDAO;
import com.want.demospringboot.dto.AccountDTO;
import com.want.demospringboot.dto.LoginUserInfo;
import com.want.demospringboot.service.AccountService;
import com.want.demospringboot.utils.CommonConstant;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	@Override
	public List<AccountDTO> list() {
		return accountDAO.findAll();
	}

	@Override
	public AccountDTO save(AccountDTO account) {
		return accountDAO.save(account);
	}

	@Override
	public void delete(Long id) {
		accountDAO.deleteById(id);
	}

	@Override
	public LoginUserInfo signIn(String username, String password) {
		Long id = accountDAO.getId(username, password);
		LoginUserInfo loginUserDTO = new LoginUserInfo();
		if (id != null) {
			String jwt = Jwts.builder().setSubject(String.valueOf(id))
					.setExpiration(new Date(System.currentTimeMillis() + CommonConstant.EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, CommonConstant.SECRET_KEY).compact();
			loginUserDTO.setJwt(jwt);
			loginUserDTO.setUsername(username);
			loginUserDTO.setId(id);
		}
		return loginUserDTO;
	}

	@Override
	public AccountDTO queryUserInfo(Long id) {
		return accountDAO.queryUserInfo(id);
	}

	@Override
	public Boolean checkUsernameExist(String username) {
		return accountDAO.checkUsernameExist(username) > 0 ? true : false;
	}

}
