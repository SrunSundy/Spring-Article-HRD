package com.hrd.article.servicesimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hrd.article.entities.UserDTO;
import com.hrd.article.services.UserServices;

public class UserDAO implements UserServices {

	@Autowired
	private JdbcTemplate jdbctemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbctemplate = jdbcTemplate;
	}
	
	
	public List<UserDTO> listUser() {
		return this.jdbctemplate.query("SELECT * FROM tbuser", new RowMapper<UserDTO>() {
			public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserDTO user = new UserDTO();
				user.setUid(rs.getInt("uid"));
				user.setUname(rs.getString("uname"));
				user.setUemail(rs.getString("uemail"));
				user.setUpassword(rs.getString("upassword"));
				user.setUgender(rs.getString("ugender"));
				user.setUtype(rs.getInt("utype"));
				user.setUstatus(rs.getInt("ustatus"));
				return user;
			}
		});
	}

	public int insertUser(UserDTO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int disableUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int enableUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int editUser(UserDTO user, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countUser() {
		// TODO Auto-generated method stub
		return 0;
	}

}
