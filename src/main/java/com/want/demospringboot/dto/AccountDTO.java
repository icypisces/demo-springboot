package com.want.demospringboot.dto;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@Entity
//@Table(name = "account")
@ApiModel(value = "UserAcount")
public class AccountDTO {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id", hidden = true)
	private Long id;

//	@Column(name = "user_name")
	@ApiModelProperty(value = "使用者帳號")
	private String username;

//	@Column(name = "password")
	@ApiModelProperty(value = "使用者密碼", hidden = true)
	private String password;

//	@Column(name = "email_Address")
	@ApiModelProperty(value = "使用者Email")
	private String emailAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
