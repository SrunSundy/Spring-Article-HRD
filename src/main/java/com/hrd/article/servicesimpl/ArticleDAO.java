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
import com.hrd.article.services.ArtitcleServices;

@Repository
public class ArticleDAO implements ArtitcleServices{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ArticleDTO> listArticles(int pages, String key) {
		int offset=(pages*10)-10;
		return jdbcTemplate.query("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE UPPER(ntitle) LIKE UPPER(?) ORDER BY nid LIMIT 10 OFFSET ?",
			   new Object[]{key+"%", offset}, new UserMapper());
	}

	public ArticleDTO listArticle(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE nid=?", new Object[]{id}, new UserMapper());
	}

	public int updateArticle(ArticleDTO article) {
		return jdbcTemplate.update("UPDATE tbnews SET ntitle=?, ndescription=?, ncontents=?, nimage=?, nuid=?, ncid=? WHERE nid=?",
			   article.getTitle(), article.getDescription(), article.getContents(), article.getImage(),  article.getUser().getUid(), article.getCategory().getId(), article.getId());
	}

	public int insertArticle(ArticleDTO article) {
		return jdbcTemplate.update("INSERT INTO tbnews(ntitle, ndescription, ncontents, nimage, nuid, ncid) VALUES(?,?,?,?,?,?)", 
				article.getTitle(), article.getDescription(), article.getContents(), article.getImage(), article.getUser().getUid(), article.getCategory().getId());
	}

	public int deleteArticle(int id) {
		return jdbcTemplate.update("DELETE FROM tbnews WHERE nid=?",id);
	}

	public List<ArticleDTO> searchArticle(String key) {
		return jdbcTemplate.query("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE UPPER(ntitle) LIKE UPPER(?)", new Object[]{key+"%"}, new UserMapper());
	}
	
	
	public List<ArticleDTO> listArticles(String key, int pages, int uid, int cid){
		
		int offset=(pages*10)-10;
		
		if(cid==0 && uid==0)
			return jdbcTemplate.query("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE UPPER(ntitle) LIKE UPPER(?) ORDER BY nid LIMIT 10 OFFSET ?",
				   new Object[]{"%"+key+"%", offset}, new UserMapper());
		
		if(cid==0)
			return jdbcTemplate.query("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE uid=? AND UPPER(ntitle) LIKE UPPER(?) ORDER BY nid LIMIT 10 OFFSET ?",
				   new Object[]{uid, "%"+key+"%", offset}, new UserMapper());
		
		return jdbcTemplate.query("SELECT * FROM tbnews n INNER JOIN tbuser u ON n.nuid=u.uid INNER JOIN tbcategory c ON c.cid=n.ncid WHERE cid=? AND UPPER(ntitle) LIKE UPPER(?) ORDER BY nid LIMIT 10 OFFSET ?",
			   new Object[]{cid,"%"+key+"%", offset}, new UserMapper());
	}
	
	
	public int getArticleRow(String key) {
		return jdbcTemplate.queryForObject("SELECT COUNT(nid) FROM tbnews WHERE LOWER(ntitle) LIKE ?",new Object[]{key+"%"}, int.class);
	}
	
	public int updateStatus(int id, int status) {
		return jdbcTemplate.update("UPDATE tbnews SET nstatus=? WHERE nid=?",
				  status,id);
		
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
			user.setUimage(rs.getString("uimage"));
			user.setUname(rs.getString("uname"));
				
			CategoryDTO category = new CategoryDTO();
			category.setId(rs.getInt("cid"));
			category.setName(rs.getString("cname"));
			
			article.setUser(user);
			article.setCategory(category);
			
			return article;
		}
	}

	
}
