package com.hrd.article.controller.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrd.article.entities.CategoryDTO;
import com.hrd.article.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * Delete Category 
	 */
	@RequestMapping ( value = {"/deletecategory/{id}"}, method = RequestMethod.DELETE )
	public ResponseEntity<Map<String,Object>> deleteCategory(@PathVariable("id") int id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if ( categoryService.isDeleteCategory(id) ){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "FAIL");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * Change Status Category 
	 */
	@RequestMapping ( value = {"/statuscategory/{id}/{status}"}, method = RequestMethod.PUT )
	public ResponseEntity<Map<String,Object>> statusCategory(@PathVariable("id") int id,@PathVariable("status") int status){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if ( categoryService.isStatus(id,status) ){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "FAIL");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * Add Category
	 */
	@RequestMapping ( value = {"/addcategory"}, method = RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> addCategory(@RequestBody CategoryDTO category){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if ( categoryService.isInsertCategory(category) ){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "FAIL");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * Update Category
	 */
	@RequestMapping ( value = {"/updatecategory"}, method = RequestMethod.PUT )
	public ResponseEntity<Map<String,Object>> upateCategory(@RequestBody CategoryDTO category){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if ( categoryService.isUpdateCategory(category) ){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "FAIL");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * List All Category
	 */
	@RequestMapping ( value = {"/listcategory"}, method = RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> listCategory(){
		List<CategoryDTO> list = categoryService.listCategory();
		Map<String, Object> map = new HashMap<String, Object>();
		if ( !list.isEmpty() ){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			map.put("RESPONSE_DATA" , list);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "FAIL");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		map.put("RESPONSE_DATA", null);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * Get One Row Category
	 */
	@RequestMapping ( value = {"/getcategory/{id}"}, method = RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> getCategory(@PathVariable("id") int id){
		CategoryDTO category = categoryService.getCategory(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if ( category != null ){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			map.put("RESPONSE_DATA" , category);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "FAIL");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		map.put("RESPONSE_DATA", null);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * Search Category With Name
	 */
	@RequestMapping ( value = {"/searchcategory"}, method = RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> searchCategory(@RequestParam("name") String name){
		List<CategoryDTO> list = categoryService.searchCategoryByName(name);
		Map<String, Object> map = new HashMap<String, Object>();
		if ( !list.isEmpty()){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			map.put("RESPONSE_DATA" , list);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "FAIL");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		map.put("RESPONSE_DATA", null);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	
}
