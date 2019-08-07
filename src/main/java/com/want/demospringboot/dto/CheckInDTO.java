package com.want.demospringboot.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CheckInData")
public class CheckInDTO {

	@ApiModelProperty(value = "sid", hidden = true)
	private Long sid;

	@ApiModelProperty(value = "userId", hidden = true)
	private Long userId;

	@ApiModelProperty(value = "簽到時間")
	private Date checkInTime;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

}
