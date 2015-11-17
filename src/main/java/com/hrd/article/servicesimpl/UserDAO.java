package com.hrd.article.servicesimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hrd.article.entities.UserDTO;
import com.hrd.article.services.UserServices;

//for use @ autowired natation
@Repository
public class UserDAO implements UserServices {

		
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<UserDTO> listUser() {
		return jdbcTemplate.query("SELECT * FROM tbuser",
				new RowMapper<UserDTO>(){
					public UserDTO mapRow(ResultSet rs, int arg1) throws SQLException {
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
		return jdbcTemplate.update("INSERT INTO tbuser(uname,upassword,uemail,ugender,utype,ustatus,uimage) VALUES(?,?,?,?,?,?,?)",user.getUname(),user.getUpassword(),user.getUemail(),user.getUgender(),user.getUtype(),user.getUstatus(),user.getUimage());
	}

	public int disableUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int enableUser(int id) {
		
		return 0;
	}

	public int editUser(UserDTO user, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countUser() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbuser", Integer.class);
	}

	public UserDTO getUser(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM tbuser WHERE id=?",new Object[]{id},
				new RowMapper<UserDTO>(){
					public UserDTO mapRow(ResultSet rs, int arg1) throws SQLException {
						UserDTO user=new UserDTO();
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

	

}
