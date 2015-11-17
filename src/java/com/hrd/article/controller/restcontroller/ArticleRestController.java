package com.hrd.article.controller.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrd.article.entities.UserDTO;
import com.hrd.article.services.UserServices;


@RestController
@RequestMapping("api/")
public class ArticleRestController {
	@Autowired
	private UserServices userservice;
	// list user
	/*@RequestMapping(value = "/listuser", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listUser() {
		ArrayList<User> list = userservice.listUser();
		Map<String, Object> map = new HashMap<String, Object>();
		if (list.isEmpty()) {
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "USER HAS BEEN NOT FOUNDS");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "USER HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA", list);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}*/
}
