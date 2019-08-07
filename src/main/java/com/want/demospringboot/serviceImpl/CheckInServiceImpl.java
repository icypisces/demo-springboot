package com.want.demospringboot.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.demospringboot.dto.CheckInDTO;
import com.want.demospringboot.mapper.CheckInMapper;
import com.want.demospringboot.pojo.Page;
import com.want.demospringboot.service.CheckInService;
import com.want.demospringboot.utils.PageMethod;

@Service
public class CheckInServiceImpl implements CheckInService {
		
	@Autowired
	private CheckInMapper checkInMapper;
	
	@Override
	public List<CheckInDTO> queryAllcheckInData(Page page) {
		return checkInMapper.queryAllcheckInData(PageMethod.getRowBounds(page));
	}
	
	@Override
	public Boolean checkIn(CheckInDTO checkIn) {
		return checkInMapper.insert(checkIn) == 1 ? true : false;
	}
	
}
