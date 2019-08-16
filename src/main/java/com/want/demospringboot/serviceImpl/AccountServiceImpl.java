package com.want.demospringboot.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.want.demospringboot.dao.AccountDAO;
import com.want.demospringboot.dto.AccountDTO;
import com.want.demospringboot.dto.LoginUserInfo;
import com.want.demospringboot.mapper.AccountMapper;
import com.want.demospringboot.service.AccountService;
import com.want.demospringboot.utils.CommonConstant;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AccountServiceImpl implements AccountService {
		
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public Iterable<AccountDTO> list(Pageable pageable) {
		return accountDAO.findAll(pageable);
//		return accountMapper.queryAllAccount();
	}
	
	@Override
	public Boolean insert(AccountDTO account) {
		return accountMapper.insert(account) == 1 ? true : false;
	}
	
	@Override
	public void delete(Long id) {
		accountMapper.delete(id);
//		accountDAO.deleteById(id);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity signUp(String username, String password, String emailAddress) {
		AccountDTO account = new AccountDTO();
		account.setUsername(username);
		account.setPassword(password);
		account.setEmailAddress(emailAddress);
		if (accountMapper.checkUsernameExist(account.getUsername()) > 0) {
			return ResponseEntity.ok("此使用者名稱已註冊，請重新輸入: " + account.getUsername());
		} else if (accountMapper.insert(account) == 1 ) {
			return ResponseEntity.ok(account.getUsername() + "註冊成功");
		} else {
			return ResponseEntity.ok(account.getUsername() + "註冊失敗");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity signIn(String username, String password) {
		Map<String, Object> result = new HashMap<>();
		AccountDTO account = accountMapper.queryUserInfoByUsername(username);
		if (account == null) {
			result.put("status", false);
			result.put("msg", "帳號錯誤");
		} else if (!password.equals(account.getPassword())){
			result.put("status", false);
			result.put("msg", "密碼錯誤");
		} else {
			LoginUserInfo loginUserDTO = new LoginUserInfo();
			Long id = account.getId();
			String jwt = Jwts.builder()
								.setSubject(String.valueOf(id))
								.setExpiration(new Date(System.currentTimeMillis() + CommonConstant.EXPIRATION_TIME))
								.signWith(SignatureAlgorithm.HS512, CommonConstant.SECRET_KEY)
								.compact();
			loginUserDTO.setJwt(jwt);
			loginUserDTO.setUsername(username);
			result.put("status", true);
			result.put("loginUserInfo", loginUserDTO);
		}
		return ResponseEntity.ok(result);
	}
	
	@Override
	public String queryUserInfo(HttpServletRequest request) {
		return "id: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	}
}
