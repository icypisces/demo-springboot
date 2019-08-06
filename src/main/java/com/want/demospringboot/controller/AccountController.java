package com.want.demospringboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.demospringboot.dto.AccountDTO;
import com.want.demospringboot.dto.LoginUserInfo;
import com.want.demospringboot.serviceImpl.AccountServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "帳號管理")
@RestController
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private AccountServiceImpl accountService;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "註冊", notes = "註冊帳號")
	@PostMapping("/signUp")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "使用者名稱", required = true, paramType = "query"),
        @ApiImplicitParam(name = "password", value = "使用者密碼", required = true, paramType = "query"),
        @ApiImplicitParam(name = "emailAddress", value = "Email", required = true, paramType = "query")
	})
	public ResponseEntity signUp(HttpServletRequest request){
		AccountDTO account = new AccountDTO();
		account.setUsername(request.getParameter("username"));
		account.setPassword(request.getParameter("password"));
		account.setEmailAddress(request.getParameter("emailAddress"));
		if (accountService.checkUsernameExist(account.getUsername())) {
			return ResponseEntity.ok("此使用者名稱已註冊，請重新輸入: " + account.getUsername());
		} else if (accountService.insert(account)) {
			return ResponseEntity.ok(account.getUsername() + "註冊成功");
		} else {
			return ResponseEntity.ok(account.getUsername() + "註冊失敗");
		}
	}
	
	@ApiOperation(value = "登入", notes = "登入帳號")
	@PostMapping("/signIn")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "使用者名稱", required = true, paramType = "query"),
        @ApiImplicitParam(name = "password", value = "使用者密碼", required = true, paramType = "query")
	})
	public LoginUserInfo signIn(HttpServletRequest request) {
		return accountService.signIn(request.getParameter("username"), request.getParameter("password"));
	}
	
	@ApiOperation(value = "查詢使用者資訊", notes = "根據ID查詢使用者資訊")
	@GetMapping("/queryUserInfo")
	public AccountDTO queryUserInfo(@ApiParam(value = "使用者id", required = true) Long id) {
		return accountService.queryUserInfo(id);
	}

}
