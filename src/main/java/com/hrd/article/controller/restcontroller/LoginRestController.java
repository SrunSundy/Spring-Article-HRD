package com.hrd.article.controller.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrd.article.entities.UserDTO;
import com.hrd.article.services.LoginFormService;

@Controller
@RequestMapping(value={"/login/"})
public class LoginRestController {
	
	@Autowired
	private LoginFormService loginFormService;
	
	/**
	 * 
	 * Get One Row Category
	 */
	@RequestMapping ( value = {"/login"}, method = RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> getCategory(@RequestParam("email") String email, @RequestParam("password") String pwd){
		
		System.out.println("login >>>>");
		UserDTO user = loginFormService.getUser(email, pwd);
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ( user != null ){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			map.put("RESPONSE_DATA" , user);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "FAIL");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		map.put("RESPONSE_DATA", null);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
		
	}
	
}
