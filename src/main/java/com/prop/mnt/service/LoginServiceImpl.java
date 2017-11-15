package com.prop.mnt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prop.mnt.dao.LoginDao;
import com.prop.mnt.model.Modules;
import com.prop.mnt.model.User;

public class LoginServiceImpl implements LoginService {
	LoginDao logDao=new LoginDao();
	
	public User checkLogon(String userid, String pw){
		User u=null;
		try{
		u=logDao.checkLogon(userid, pw);
		}catch(Exception e){e.getMessage();}
		
		return u;
	}
	
	
	public List<Modules> selectPropRels(Integer userId,String owner){
		 List<Modules> mods=new ArrayList<Modules>();
		try{
			String qry="";
			if(owner==null)
				qry="SELECT distinct(r.relation_code) FROM prop_user_rel r join  prop_user u on u.relation_code= r.relation_code and userid="+userId+" where u.status='A'";
			else
			   qry="SELECT distinct(r.relation_code) FROM prop_user_rel r join  prop_user u on u.relation_code= r.relation_code and userid="+userId+" or r.relation_code='OWN' where u.status='A'";
			
			Map<Integer,Modules> modules=logDao.selectPropRels(qry);
		
         for(Integer id:modules.keySet()){
       	  mods.add(modules.get(id));
         }
		}catch(Exception e){e.getMessage();}
		return mods;
	}
	
	@Override
	public void updateLogout(Object userid) {
		logDao.updateLogout(userid);
	}
}
