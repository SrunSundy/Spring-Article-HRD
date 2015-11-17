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

import com.hrd.article.entities.ArticleDTO;
import com.hrd.article.services.ArtitcleServices;

@RestController
@RequestMapping("/article")
public class AritcleController {
	
	
	@Autowired
	private ArtitcleServices articleservice;
	
	
	@RequestMapping(value="/listarticles", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listArticles(){
		List<ArticleDTO> articles = articleservice.listArticles(1,"s");
		Map<String, Object> map = new HashMap<String,Object>();
		if(articles.isEmpty()){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "ARTICLE NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "ARITCLE HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA", articleservice.listArticles(1,"s"));
		return new ResponseEntity<Map<String,Object>>
									(map,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/listarticle", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> listArticle(@RequestParam("articleid") int id ){
		ArticleDTO article= articleservice.listArticle(id);
		Map<String, Object> map = new HashMap<String,Object>();
		if(article == null){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "ARTICLE NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "ARITCLE HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA", articleservice.listArticles(1,"s"));
		return new ResponseEntity<Map<String,Object>>
									(map,HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/updatearticle/{articleid}", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> updateArticle(@RequestBody ArticleDTO article, @PathVariable("articleid") int id){
		article.setId(id);
		Map<String, Object> map  = new HashMap<String, Object>();
		if(articleservice.updateArticle(article)==1){
			map.put("MESSAGE","ARTICLE HAS BEEN UPDATED.");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.OK);
		}else{
			map.put("MESSAGE","ARTICLE HAS NOT BEEN UPDATED.");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/insertarticle", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> insertArticle(@RequestBody ArticleDTO article){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(articleservice.insertArticle(article)==1){
			map.put("MESSAGE","ARTICLE HAS BEEN INSERTED.");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.CREATED);
		}else{
			map.put("MESSAGE","ERROR INSERTING");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/deletearticle/{articleid}", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> deleteStudent(@PathVariable("articleid") int id){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		if(articleservice.deleteArticle(id)==1){
			map.put("MESSAGE","ARTICLE HAS BEEN DELETED.");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.OK);
		}else{
			map.put("MESSAGE","ARTICLE HAS NOT BEEN DELETED.");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.NOT_FOUND);
		}
	}
	
	
}
