package com.prop.mnt.service;

import java.util.List;

import com.prop.mnt.model.Modules;
import com.prop.mnt.model.User;

public interface LoginService {

	public User checkLogon(String userid, String pw);
	public List<Modules> selectPropRels(Integer userId,String owner);
	 public void updateLogout(Object userid);
	
}
