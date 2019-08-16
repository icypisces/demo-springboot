package com.want.demospringboot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.want.demospringboot.dto.CheckInDTO;

@Service
public interface CheckInService {
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity checkIn(HttpServletRequest request);

	public List<CheckInDTO> queryCheckInData(HttpServletRequest request);

}
