package com.hrd.article.servicesimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hrd.article.entities.CategoryDTO;
import com.hrd.article.services.CategoryService;

@Repository
public class CategoryServiceImpl implements CategoryService{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	/**
	 * List All Category
	 */
	public List<CategoryDTO> listCategory() {
		List<CategoryDTO> lst = new ArrayList<CategoryDTO>();
		
		String sql = "SELECT cid, cname, cdescription, cstatus FROM tbcategory;";
		lst = jdbcTemplate.query(sql , new CategoryRowMapper());
		if ( !lst.isEmpty() ){
			return lst;
		}
		return null;
	}

	/**
	 * Get One Record Of Category
	 */
	public CategoryDTO getCategory(int id) {
		
		String sql = "SELECT cid, cname, cdescription, cstatus FROM tbcategory WHERE cid = ? ;";
		Object[] obj = { id };
		CategoryDTO category;
		category = jdbcTemplate.query(sql , obj, new CategoryResultSetExtractor());
		if ( category != null ){
			return category;
		}
		return null;
	}
	

	/**
	 * Insert Category
	 */
	public boolean isInsertCategory(CategoryDTO category) {
		
		final String sql="INSERT INTO tbcategory(cname, cdescription, cstatus) VALUES(?,?,?);";
		Object[] obj = { category.getName(), category.getDescription(), category.getStatus() }; 
		int result = jdbcTemplate.update(sql, obj);

		if ( result > 0){
			return true;
		}
		return false;
		
		/**
		 * Insert Auto Get Primary Key
		 */
		
/*		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"cid"});
		            ps.setString(1, "ravuth");
		            return ps;
		        }
		    },
		    keyHolder);
		
		System.out.println("key is :" + keyHolder.getKey());*/
		
	}

	
	/**
	 * Delete Category
	 */
	public boolean isDeleteCategory(int id) {
		
		String sql = "DELETE FROM tbcategory WHERE cid = ? "; 
		int result = jdbcTemplate.update(sql, id);
		if ( result > 0) {
			return true;
		}
		return false;
	}

	
	/**
	 * Update Category
	 */
	public boolean isUpdateCategory(CategoryDTO category) {
		String sql="UPDATE tbcategory SET cname = ?, cdescription = ?, cstatus = ? WHERE cid = ?;";
		Object[] obj = { category.getName(), category.getDescription(), category.getStatus() ,category.getId() }; 
		int result = jdbcTemplate.update(sql, obj);
		
		if ( result > 0){
			return true;
		}
		return false;
	}

	
	/**
	 * Search Category By Category Name
	 */
	public List<CategoryDTO> searchCategoryByName(String name) {
		List<CategoryDTO> lst = new ArrayList<CategoryDTO>();
		
		String sql = "SELECT cid, cname, cdescription, cstatus FROM tbcategory WHERE cname like ?;";
		Object[] obj = { "%"+name+"%" };
		lst = jdbcTemplate.query(sql ,obj, new CategoryRowMapper());
		if ( !lst.isEmpty() ){
			return lst;
		}
		return null;
	}

	
	/**
	 * Total Category Record
	 */
	public int getTotalCategory() {
		String sql = "SELECT COUNT(*) FROM tbcategory;";
		int total = jdbcTemplate.queryForObject(sql,Integer.class);
		return total;
	}
	
	/**
	 * Change Status Category
	 */
		public boolean isStatus(int id,int status) {
			String sql = "UPDATE tbcategory SET cstatus = ? WHERE cid = ? ;";
			
			if ( status == 0){
				status = 0;
			}else {
				status = 1;
			}
			
			System.out.println("id is: "+id);
			System.out.println("status is: "+ status);
			
			Object[] obj = { status , id };
			
			int result = jdbcTemplate.update(sql, obj);
			if ( result > 0 ){
				return true;
			}
			return false;
		}

}

/**
 * 
 * @author PC1
 * Map Row Of Category
 * Get Data Multiple Rows
 */
 final class CategoryRowMapper implements RowMapper<CategoryDTO>{

	public CategoryDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		CategoryDTO category = new CategoryDTO();
		category.setId(rs.getInt("cid"));
		category.setName(rs.getString("cname"));
		category.setDescription(rs.getString("cdescription"));
		category.setStatus(rs.getInt("cstatus"));
		
		return category;
	}
}
 
 
 
 
 /**
  * 
  * @author PC1
  * Extract Data One Row
  */
 final class CategoryResultSetExtractor implements ResultSetExtractor<CategoryDTO>{

	public CategoryDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
		while (rs.next()) {
			CategoryDTO category = new CategoryDTO();
			category.setId(rs.getInt("cid"));
			category.setName(rs.getString("cname"));
			category.setDescription(rs.getString("cdescription"));
			category.setStatus(rs.getInt("cstatus"));
			
			return category;
		}
		return null;
	}
	 
 }

