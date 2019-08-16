package com.want.demospringboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.want.demospringboot.dto.CheckInDTO;

@Mapper
public interface CheckInMapper {
	
	@Select("SELECT * FROM check_in.check_in WHERE user_id = #{user_id}")
	@Results({
        @Result(property = "userId", column = "user_id"),
        @Result(property = "checkInTime", column = "check_in")
	})
	List<CheckInDTO> queryCheckInData(@Param("user_id") Long user_id, RowBounds rowBounds);
	
	@Insert("INSERT INTO check_in.check_in(user_id, check_in) VALUES(#{userId}, #{checkInTime})")
	long insert(CheckInDTO checkInDTO);
	
}
