package com.want.demospringboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.demospringboot.serviceImpl.AccountServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
		return accountService.signUp(request.getParameter("username"), request.getParameter("password"), request.getParameter("emailAddress"));
		
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "登入", notes = "登入帳號")
	@PostMapping("/signIn")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "使用者名稱", required = true, paramType = "query"),
        @ApiImplicitParam(name = "password", value = "使用者密碼", required = true, paramType = "query")
	})
	public ResponseEntity signIn(HttpServletRequest request) {
		return accountService.signIn(request.getParameter("username"), request.getParameter("password"));
	}
	
	@ApiOperation(value = "查詢使用者資訊", notes = "根據ID查詢使用者資訊")
	@GetMapping("/queryUserInfo")
	public String ryUserInfo(HttpServletRequest request) {
		return accountService.queryUserInfo(request);
	}

}
