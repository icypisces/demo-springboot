package com.want.demospringboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.want.demospringboot.dto.CheckInDTO;
import com.want.demospringboot.pojo.Page;

@Service
public interface CheckInService {
	
	public List<CheckInDTO> queryAllcheckInData(Page page);
	
	public Boolean checkIn(CheckInDTO checkIn);
	

}
