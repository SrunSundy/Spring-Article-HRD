package com.hrd.article.servicesimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hrd.article.entities.ArticleDTO;
import com.hrd.article.entities.CategoryDTO;
import com.hrd.article.entities.UserDTO;
import com.hrd.article.services.ArtitcleServices;

@Repository
public class ArticleDAO implements ArtitcleServices{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ArticleDTO> listArticles(int page,String key) {
		int offset = ( page * 10 ) - 10;
		
		if((page == 0 && key.equals("*")) || page == 0){
			if(page == 0 && key.equals("*")) key = "%";
			return jdbcTemplate.query("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE UPPER(ntitle) LIKE UPPER(?) ORDER BY npin",
				   new Object[]{"%"+key+"%"}, new UserMapper());
		}
		else if((page != 0 && key.equals("*")))
			key = "%";
		
		return jdbcTemplate.query("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE UPPER(ntitle) LIKE UPPER(?) ORDER BY npin LIMIT 10 OFFSET ?",
			   new Object[]{"%"+key+"%", offset}, new UserMapper());
	
	}
	
	public int insertArticle(ArticleDTO article) {
		return jdbcTemplate.update("INSERT INTO tbnews(ntitle, ndescription, ncontents, nimage, nuid, ncid) VALUES(?,?,?,?,?,?)", 
				article.getTitle(), article.getDescription(), article.getContents(), article.getImage(), article.getUser().getUid(), article.getCategory().getId());
	}
	
	public int updateArticle(ArticleDTO article) {

		return jdbcTemplate.update("UPDATE tbnews SET ntitle=?, ndescription=?, ncontents=?, nimage=?, nuid=?, ncid=? WHERE nid=?",
			   article.getTitle(), article.getDescription(), article.getContents(), article.getImage(),  article.getUser().getUid(), article.getCategory().getId(), article.getId());
	

	}
	

	public int deleteArticle(int id) {
		return jdbcTemplate.update("DELETE FROM tbnews WHERE nid=?",id);
	}
	
	
	public ArticleDTO listArticle(int id) {
		try{
			return jdbcTemplate.queryForObject("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE UPPER(n.ntitle) LIKE UPPER(?)  ORDER BY n.nid LIMIT ? OFFSET ?",new Object[]{id}, new UserMapper());
		} catch (IncorrectResultSizeDataAccessException ex) {
            return null;
          // print idSkill, lang.toLanguageTag(), extColumn, extName here
        }
	}

	
	public int enableArticle(int id) {
		return jdbcTemplate.update("UPDATE tbnews SET nstatus=1 WHERE nid=?",
				  id);
		
	}
	public int disableArticle(int id) {
		return jdbcTemplate.update("UPDATE tbnews SET nstatus=0 WHERE nid=?",
				  id);
		
	}

	private static final class UserMapper implements RowMapper<ArticleDTO>{		
		public ArticleDTO mapRow(ResultSet rs, int rowNumber) throws SQLException {
			ArticleDTO article = new ArticleDTO();
			
			article.setId(rs.getInt("nid"));
			article.setTitle(rs.getString("ntitle"));
			article.setDescription(rs.getString("ndescription"));
			article.setImage(rs.getString("nimage"));
			article.setPostdate(rs.getDate("npostdate"));
			article.setContents(rs.getString("ncontents"));
			article.setStatus(rs.getInt("nstatus"));
			
			UserDTO user = new UserDTO();
			user.setUid(rs.getInt("uid"));

			user.setUname(rs.getString("uname"));
			user.setUtype(rs.getInt("utype"));
			user.setUimage(rs.getString("uimage"));
			

			CategoryDTO category = new CategoryDTO();
			category.setId(rs.getInt("cid"));
			category.setName(rs.getString("cname"));
			
			article.setUser(user);
			article.setCategory(category);
			
			return article;
		}
	}

	




	

}
