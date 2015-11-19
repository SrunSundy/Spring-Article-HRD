package com.hrd.article.controller.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrd.article.entities.ArticleDTO;
import com.hrd.article.services.ArtitcleServices;

@RestController
@RequestMapping("/admin")
public class AritcleController {
	
	
	@Autowired
	private ArtitcleServices articleservice;
	
	
	@RequestMapping(value="/listarticles", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listArticles(){
		List<ArticleDTO> articles = articleservice.listArticles(1,"");
		Map<String, Object> map = new HashMap<String,Object>();
		if(articles.isEmpty()){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "ARTICLE NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "ARITCLE HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA",articles);
		return new ResponseEntity<Map<String,Object>>
									(map,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/listarticle", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listArticle(@RequestParam("articleid") int id ){
		System.err.println(articleservice.listArticle(id));
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
		map.put("RESPONSE_DATA", article);
		return new ResponseEntity<Map<String,Object>>
									(map,HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/updatearticle", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> updateArticle(@RequestBody ArticleDTO article){

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
	
	private static final Logger logger = LoggerFactory.getLogger(AritcleController.class);
	@RequestMapping(value="/insertarticle", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> insertArticle(@RequestBody ArticleDTO article){
	
		Map<String, Object> map  = new HashMap<String, Object>();
	
		if(articleservice.insertArticle(article)==1){
			
			map.put("MESSAGE","ARTICLE HAS BEEN INSERTED.");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.OK);
		}else{
			map.put("MESSAGE","ARTICLE HAS NOT BEEN INSERTED.");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(value="/deletearticle", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> deleteStudent(@RequestParam("articleid") int id){
		
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
	
	
	@RequestMapping(value="/getarticlerow", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> getArticleRow(@RequestParam("acontent") String word){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		int articlerow=articleservice.getArticleRow(word);
			map.put("MESSAGE","ARITCLE ROW");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("RESPONSE_DATA", articlerow);
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/displayarticlepage", method= RequestMethod.POST )
	public ResponseEntity<Map<String,Object>> getArticlePage(@RequestParam("acontent") String word,@RequestParam("page") int page){
		System.err.print("sdfdfsdfsf");
		List<ArticleDTO> articles = articleservice.listArticles(page,word);
		Map<String, Object> map = new HashMap<String,Object>();
		/*if(articles.isEmpty()){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "ARTICLE NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}*/
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "ARITCLE HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA",articles);
		return new ResponseEntity<Map<String,Object>>
									(map,HttpStatus.OK);	
	}
	
	
}
