package com.hrd.article.services;


import java.util.List;

import com.hrd.article.entities.UserDTO;

public interface UserServices {
	public List<UserDTO> listUser();
	public int insertUser(UserDTO user);
	public int disableUser(int id);
	public int enableUser(int id);
	public int editUser(UserDTO user,int id);
	public int countUser();
	

}
