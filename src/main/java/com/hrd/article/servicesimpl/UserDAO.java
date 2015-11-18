package com.hrd.article.servicesimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hrd.article.entities.ArticleDTO;
import com.hrd.article.entities.CategoryDTO;
import com.hrd.article.entities.UserDTO;
import com.hrd.article.services.UserServices;

@Repository
public class UserDAO implements UserServices {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<UserDTO> listUser(int pages,String key) {
		int offset=(pages*10)-10;
		return jdbcTemplate.query("SELECT * FROM tbuser WHERE UPPER(uname) LIKE UPPER(?) LIMIT 10 OFFSET ? ", new Object[]{"%"+key+"%", offset}, new UserMapper());
	}

	public int insertUser(UserDTO user) {
		String sql="INSERT INTO tbuser(uname,upassword,uemail,ugender,utype,ustatus,uimage) VALUES(?,?,?,?,?,?,?)";
		Object[] obj={user.getUname(),user.getUpassword(),user.getUemail(),user.getUgender(),user.getUtype(),user.getUstatus(),user.getUimage()};
		return jdbcTemplate.update(sql,obj);
	}

	public int disableUser(int id) {
		String sql="UPDATE tbuser SET ustatus=0 WHERE uid=?";
		return jdbcTemplate.update(sql,id);
	}

	public int enableUser(int id) {
		String sql="UPDATE tbuser SET ustatus=1 WHERE uid=?";
		return jdbcTemplate.update(sql,id);
	}

	public int editUser(UserDTO user) {
		if(user.getUimage()==""){
			String sql="UPDATE tbuser SET uname=?,upassword=?,uemail=?,ugender=?,utype=?,ustatus=? WHERE uid=?";
			Object[] obj={user.getUname(),user.getUpassword(),user.getUemail(),user.getUgender(),user.getUtype(),user.getUstatus(),user.getUid()};
			return jdbcTemplate.update(sql,obj);
		}else{
			String sql="UPDATE tbuser SET uname=?,upassword=?,uemail=?,ugender=?,utype=?,ustatus=?,uimage=? WHERE uid=?";
			Object[] obj={user.getUname(),user.getUpassword(),user.getUemail(),user.getUgender(),user.getUtype(),user.getUstatus(),user.getUimage(),user.getUid()};
			return jdbcTemplate.update(sql,obj);
		}
		
	}

	public int countUser() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbuser", Integer.class);
	}

	public UserDTO getUser(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM tbuser WHERE uid=?",new Object[]{id},new UserMapper());
	}
	
	private static final class UserMapper implements RowMapper<UserDTO>{		
		public UserDTO mapRow(ResultSet rs, int rowNumber) throws SQLException {
			UserDTO user=new UserDTO();
			user.setUid(rs.getInt("uid"));
			user.setUname(rs.getString("uname"));
			user.setUemail(rs.getString("uemail"));
			user.setUpassword(rs.getString("upassword"));
			user.setUgender(rs.getString("ugender"));
			user.setUtype(rs.getInt("utype"));
			user.setUstatus(rs.getInt("ustatus"));
			user.setUimage(rs.getString("uimage"));
			return user;
		}
	}

}
