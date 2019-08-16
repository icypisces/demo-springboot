package com.want.demospringboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.want.demospringboot.dto.AccountDTO;

@Mapper
public interface AccountMapper {
	
	@Select("SELECT * FROM account WHERE status = '1'")
	@Results({
        @Result(property = "id", column = "id"),
        @Result(property = "username", column = "user_name"),
        @Result(property = "password", column = "password"),
        @Result(property = "emailAddress", column = "email_Address")
	})
	List<AccountDTO> queryAllAccount();
	
	@Insert("INSERT INTO account(user_name, password, email_Address) VALUES(#{username}, #{password}, #{emailAddress})")
	long insert(AccountDTO accountDTO);
	
//	@Delete("DELETE FROM ACCOUNT WHERE id = #{id}")
	@Update("UPDATE account SET status = '0' WHERE id = #{id}")
	long delete(long id);
	
	@Select("SELECT id FROM account WHERE user_name = #{username} AND password = #{password}")
    public long getId(@Param("username") String username, @Param("password") String password);
	
	@Select("SELECT * FROM account WHERE id = #{id} AND status = '1'")
	@Results({
        @Result(property = "id", column = "id"),
        @Result(property = "username", column = "user_name"),
        @Result(property = "password", column = "password"),
        @Result(property = "emailAddress", column = "email_Address")
	})
	public AccountDTO queryUserInfo(@Param("id") Long id);
	
	@Select("SELECT count(1) FROM account WHERE user_name = #{username} AND status = '1'")
	public int checkUsernameExist(@Param("username") String username);
	
	@Select("SELECT * FROM account WHERE user_name = #{username}")
	@Results({
        @Result(property = "id", column = "id"),
        @Result(property = "username", column = "user_name"),
        @Result(property = "password", column = "password"),
        @Result(property = "emailAddress", column = "email_Address")
	})
	public AccountDTO queryUserInfoByUsername(@Param("username") String username);
	
	@Select("SELECT count(1) FROM account WHERE id = #{id} AND status = '1'")
	public int checkUserIdExist(@Param("id") Long id);
	
	@Select("SELECT user_name FROM account WHERE id = #{id} AND status = '1'")
	public String queryUsernameById(@Param("id") Long id);
}
