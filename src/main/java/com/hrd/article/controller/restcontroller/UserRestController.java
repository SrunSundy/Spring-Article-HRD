package com.hrd.article.controller.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrd.article.entities.UserDTO;
import com.hrd.article.services.UserServices;

@RestController
@RequestMapping(value = "api/user/")
public class UserRestController {
	@Autowired
	UserServices userService;
	
	//list user
	@RequestMapping(value="/getAllUser")
	public ResponseEntity<Map<String, Object>> listUser() {
	    List<UserDTO> list = userService.listUser();
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
	}
}
