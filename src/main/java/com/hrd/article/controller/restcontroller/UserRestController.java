package com.hrd.article.controller.restcontroller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.hrd.article.entities.UserDTO;
import com.hrd.article.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserServices userService;
	
	//list user
	@RequestMapping(value="/getalluser",method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listUser(@RequestBody String info ) throws ParseException {
		
		//for get data from json string
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(info);
		// get a String from the JSON object
		String Page = (String) jsonObject.get("page");
		int Pages=Integer.parseInt(Page);
		String Key = (String) jsonObject.get("key");
	

		
		List<UserDTO> list = userService.listUser(Pages,Key);
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
	//enable user
	@RequestMapping(value="/enableuser/{userid}",method=RequestMethod.PUT)
	public ResponseEntity<String> enableUser(@PathVariable("userid") int id){
		if(userService.enableUser(id)==0){
			return new ResponseEntity<String>("Failed",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	//disable user
	@RequestMapping(value="/disableuser/{userid}",method=RequestMethod.PUT)
	public ResponseEntity<String> disableUser(@PathVariable("userid") int id){
		if(userService.disableUser(id)==0){
			return new ResponseEntity<String>("Failed",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	
	//get user
	@RequestMapping(value="/getuser/{userid}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getUser(@PathVariable("userid") int id){
		UserDTO user=userService.getUser(id);
		Map<String,Object> map=new HashMap<String, Object>();
		if(user==null){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE","USER NOT FOUND");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "USER HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA", user);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
	} 
	//add user
	@RequestMapping(value="/adduser" , method = RequestMethod.POST )
	public ResponseEntity<String> addUser(UserDTO user ,  @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if(!file.isEmpty()){
			try{
				UUID uuid = UUID.randomUUID();
	            String originalFilename = file.getOriginalFilename(); 
	            String extension = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
	            String randomUUIDFileName = uuid.toString();
	            
	            String filename = randomUUIDFileName+"."+extension;
				
	            user.setUimage(filename);
	            
				byte[] bytes = file.getBytes();

				// creating the directory to store file
				String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/file/");
				System.out.println(savePath);
				File path = new File(savePath);
				if(!path.exists()){
					path.mkdir();
				}
				
				// creating the file on server
				File serverFile = new File(savePath + File.separator + filename );
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				System.out.println(serverFile.getAbsolutePath());
				System.out.println("You are successfully uploaded file " + filename);
			}catch(Exception e){
				System.out.println("You are failed to upload  => " + e.getMessage());
			}
		}else{
			user.setUimage("images.jpg");
			
			System.out.println("The file was empty!");
		}
		
		
		
		if(userService.insertUser(user) == 0){
				System.out.println("Error..... cannot added user!");
				return new ResponseEntity<String>("ERROR",HttpStatus.NOT_FOUND);
		}
		System.out.println("User has been inserted successfully.....!");
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
	}
	//edit user
	@RequestMapping(value="/edituser",method=RequestMethod.PUT)
	public ResponseEntity<String> editUser (UserDTO user ,  @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if(!file.isEmpty()){
			try{
				UUID uuid = UUID.randomUUID();
	            String originalFilename = file.getOriginalFilename(); 
	            String extension = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
	            String randomUUIDFileName = uuid.toString();
	            
	            String filename = randomUUIDFileName+"."+extension;
				
	            user.setUimage(filename);
	            
				byte[] bytes = file.getBytes();

				// creating the directory to store file
				String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/file/");
				System.out.println(savePath);
				File path = new File(savePath);
				if(!path.exists()){
					path.mkdir();
				}
				
				// creating the file on server
				File serverFile = new File(savePath + File.separator + filename );
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				System.out.println(serverFile.getAbsolutePath());
				System.out.println("You are successfully uploaded file " + filename);
			}catch(Exception e){
				System.out.println("You are failed to upload  => " + e.getMessage());
			}
		}else{
			System.out.println("The file was empty!");
			user.setUimage("");
		}
		
		
		   
		if(userService.editUser(user)==0){
				System.out.println("Error..... cannot edit user!");
				return new ResponseEntity<String>("ERROR",HttpStatus.NOT_FOUND);
		}
		System.out.println("user has been edit successfully.....!");
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
	}
}
