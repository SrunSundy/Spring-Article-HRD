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
@RequestMapping("/api/category")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * Delete Category 
	 */
	@RequestMapping ( value = {"/{id}"}, method = RequestMethod.DELETE )
	public ResponseEntity<Map<String,Object>> deleteCategory(@PathVariable("id") int id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if ( categoryService.isDeleteCategory(id) ){
			map.put("MESSAGE", "DELETE CATEGORY SUCCESS...!");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "DELETE CATEGORY FAIL...!");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * Change Status Category 
	 */
	@RequestMapping ( value = {"toggle/{status}/{id}"}, method = RequestMethod.PATCH )
	public ResponseEntity<Map<String,Object>> statusCategory(@PathVariable("status") int status ,@PathVariable("id") int id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ( status == 0 ){
			if ( categoryService.isStatusDisable(id,status) ){
				map.put("MESSAGE", "STATUS DISABLE SUCCESS...!");
				map.put("STATUS", HttpStatus.OK.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
		}else{
			if ( categoryService.isStatusEnable(id,status) ){
				map.put("MESSAGE", "STATUS ENABLE SUCCESS...!");
				map.put("STATUS", HttpStatus.OK.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
		}

		map.put("MESSAGE", "STATUS CHANGE FAIL...!");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * Add Category
	 */
	@RequestMapping ( value = {"/"}, method = RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> addCategory(@RequestBody CategoryDTO category){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if ( categoryService.isInsertCategory(category) ){
			map.put("MESSAGE", "ADD CATEGORY SUCCESS...!");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "ADD CATEGORY FAIL...!");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * Update Category
	 */
	@RequestMapping ( value = {"/"}, method = RequestMethod.PUT )
	public ResponseEntity<Map<String,Object>> upateCategory(@RequestBody CategoryDTO category){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if ( categoryService.isUpdateCategory(category) ){
			map.put("MESSAGE", "UPDATE CATEGORY SUCCESS...!");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "UPDATE CATEGORY FAIL...!");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * List All Category
	 */
	@RequestMapping ( value = {"/"}, method = RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> listCategoryAll(){
		List<CategoryDTO> list = categoryService.listCategoryAll();
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ( !list.isEmpty() ){
			map.put("MESSAGE", "LIST CATEGORY SUCCESS...!");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("RESPONSE_DATA" , list);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "LIST CATEGORY FAIL...!");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("RESPONSE_DATA", null);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	/**
	 * 
	 * List Category Page 
	 */
/*	@RequestMapping ( value = {"/{page}"}, method = RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> listCategoryPage(@PathVariable("page") int page){
		List<CategoryDTO> list = categoryService.listCategory(page);
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
	}*/
	
	/**
	 * 
	 * Get One Row Category
	 */
	@RequestMapping ( value = {"/{id}"}, method = RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> getCategory(@PathVariable("id") int id){
		CategoryDTO category = categoryService.getCategory(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if ( category != null ){
			map.put("MESSAGE", "GET CATEGORY DETAIL SUCCESS...!");
			map.put("STATUS", HttpStatus.OK);
			map.put("RESPONSE_DATA" , category);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "GET CATEGORY DETAIL FAIL...!");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("RESPONSE_DATA", null);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * Search Category With Key
	 */
	@RequestMapping ( value = {"/{page}/{key}"}, method = RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> searchCategory(@PathVariable("page") int page, @PathVariable("key") String key){
		List<CategoryDTO> list = categoryService.searchCategoryByName(page, key);
		Map<String, Object> map = new HashMap<String, Object>();
		if ( !list.isEmpty()){
			map.put("MESSAGE", "LIST CATEGORY PAGE SUCCESS...!");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("RESPONSE_DATA" , list);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "LIST CATEGORY PAGE FAIL...!");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("RESPONSE_DATA", null);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	
}
