package com.want.demospringboot.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.demospringboot.dto.CheckInDTO;
import com.want.demospringboot.pojo.Page;
import com.want.demospringboot.serviceImpl.CheckInServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "簽到記錄管理")
@RestController
@RequestMapping("checkIn")
public class CheckInController {
	
	@Autowired
	private CheckInServiceImpl checkInService;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "簽到")
	@PostMapping("/checkIn")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "使用者Id", required = true, paramType = "query")
	})
	public ResponseEntity checkIn(HttpServletRequest request){
		CheckInDTO checkInData = new CheckInDTO();
		checkInData.setUserId(Long.valueOf(request.getParameter("userId")));
		Date current = new Date(System.currentTimeMillis());
		checkInData.setCheckInTime(current);
		if (checkInService.checkIn(checkInData)){
			return ResponseEntity.ok(checkInData.getUserId() + " 打卡成功");
		} else {
			return ResponseEntity.ok(checkInData.getUserId() + " 打卡失敗");
		}
	}
	
	@ApiOperation(value = "查詢全部打卡紀錄")
	@GetMapping("/queryAllcheckInData")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "當前頁面，預設值為1", required = true, paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "頁面大小，預設值為5", required = true, paramType = "query")
	})
	public List<CheckInDTO> queryAllcheckInData(HttpServletRequest request) {
		Page page = new Page();
		page.setPageNum(Integer.valueOf(request.getParameter("pageNum") != null ? request.getParameter("pageNum") : "1"));
		page.setPageSize(Integer.valueOf(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "5"));
		return checkInService.queryAllcheckInData(page);
	}

}
