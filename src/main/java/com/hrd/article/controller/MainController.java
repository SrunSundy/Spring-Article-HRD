package com.hrd.article.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {


	
	@RequestMapping( value={"/"})
	public String getStudentList(ModelMap model) throws SQLException{
			
		model.addAttribute("message","Hellosdfsf");
		return "home";
		
	}
}
