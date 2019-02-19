package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.model.Role;
import com.model.User;


@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public User checkUser(final User user) {
		String sql = "select u.*,r.role_code  from user u left join role r on u.role_id=r.role_id where u.username = '" + user.getUsername() + "' and u.password = '" + user.getPassword() + "' " ;
		jdbcTemplate.query(sql, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			        user.setUserId(rs.getInt("user_id"));
			        user.setUsername((rs.getString("username")));
			        user.setPassword(rs.getString("password"));
			        user.setName(rs.getString("name"));
			        user.setRoleId(rs.getInt("role_id"));
			        user.setRoleCode(rs.getString("role_code"));
				 return null ;
			}
		});
		return user;
	}
	
	public String whereSql(User user){
		String sql = "";
		return sql;
	}

	public List<User> queryAllUsers() {
		String sql = "select u.*,r.role_code,r.role_name  , b.name mamberName , b.phone from user u left join role r on u.role_id=r.role_id  "
				+ "  left join member b on u.username = b.phone " ;
		final  List<User> list =   new ArrayList<>();
		jdbcTemplate.query(sql, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setRoleId(rs.getInt("role_id"));
				user.setRoleCode(rs.getString("role_code"));
				user.setRoleName(rs.getString("role_name"));
				user.setOpenid(rs.getString("openid"));
				user.setPassword(rs.getString("password"));
				user.getMember().setName(rs.getString("mamberName"));
				user.getMember().setPhone(rs.getString("phone"));
				list.add(user);
				return null ;
			}
		});
		return list;
	}

	public int queryAllUsersTotal() {
		final Integer[] total =  {0} ;
		String  sql  = "select count(*) total from user u left join role r on u.role_id=r.role_id " ;
		jdbcTemplate.query(sql, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				total[0] = rs.getInt("total");
				return null ;
			}
		});
		return total[0];
	}


	public List<Role> getAllRoleTypes() {
		String  sql  = " SELECT * FROM role " ;
		final  List<Role> list =   new ArrayList<>();
		jdbcTemplate.query(sql, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Role role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleCode(rs.getString("role_code"));
				role.setRoleName(rs.getString("role_name"));
				list.add(role);
				return null ;
			}
		});
		return list;
	}

	public void updateRole(String openid, String table , Integer role ) {
		 String updatesqlTemp = "";
		 if("member".equals(table)) {
			 updatesqlTemp = "update member set role = " + role  + " where open_id = '" + openid  + "'"; 
		 }else {
			 updatesqlTemp = "update user set role_id = " + role  + " where openid = '" + openid  + "'"; 
		 }
	        jdbcTemplate.update(updatesqlTemp);
	}

	public void deleteMember(String ids , String table ) {
		String updatesqlTemp = "";
		 if("member".equals(table)) {
			 updatesqlTemp = "delete from  member where phone in (select username from user where user_id in (" + ids  + "))"; 
		 }else {
			 updatesqlTemp = "delete from  user where username  in ( select phone from member where member_id in (" + ids + "))"; 
		 }
		 jdbcTemplate.update(updatesqlTemp);
	}
}
