package com.hrd.article.services;


import java.util.List;

import com.hrd.article.entities.UserDTO;

public interface UserServices {
	public List<UserDTO> listUser(int page,String key);
	
	public int insertUser(UserDTO user);
	
	public int disableUser(int id);
	
	public int enableUser(int id);
	
	public int editUser(UserDTO user);
	
	public int countUser();
	
	public UserDTO getUser(int id);
}
