package com.want.demospringboot.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.want.demospringboot.dto.CheckInDTO;
import com.want.demospringboot.mapper.AccountMapper;
import com.want.demospringboot.mapper.CheckInMapper;
import com.want.demospringboot.pojo.Page;
import com.want.demospringboot.service.CheckInService;
import com.want.demospringboot.utils.PageMethod;

@Service
public class CheckInServiceImpl implements CheckInService {
		
	@Autowired
	private CheckInMapper checkInMapper;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity checkIn(HttpServletRequest request) {
		CheckInDTO checkInData = new CheckInDTO();
		Long id = Long.valueOf(SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal().toString());
		String username = request.getParameter("username");
		if (accountMapper.checkUserIdExist(id) == 0 || 
				!accountMapper.queryUsernameById(id).equals(username)) {
			return ResponseEntity.ok(username + " 打卡失敗，帳號驗證失敗，請重新登入");
		} else {
			checkInData.setUserId(Long.valueOf(id));
			Date current = new Date(System.currentTimeMillis());
			checkInData.setCheckInTime(current);
			if (checkInMapper.insert(checkInData) == 1){
				return ResponseEntity.ok(username + " 打卡成功");
			} else {
				return ResponseEntity.ok(username + " 打卡失敗，請稍後再試");
			}
		}
	}
	
	@Override
	public List<CheckInDTO> queryCheckInData(HttpServletRequest request) {
		Page page = new Page();
		page.setPageNum(Integer.valueOf(request.getParameter("pageNum") != null ? request.getParameter("pageNum") : "1"));
		page.setPageSize(Integer.valueOf(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "5"));
		Long user_id = Long.valueOf(SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal().toString());
		return checkInMapper.queryCheckInData(user_id,PageMethod.getRowBounds(page));
	}
	
}
