package com.prop.mnt.logon.action;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = -5011962009065225959L;

	@Override
	public void destroy() {
		System.out.println("In destroy ");
	}

	@Override
	public void init() {
		System.out.println("In init ");
	}

	@Override
	public String intercept(ActionInvocation actionInvocation)
			throws Exception {
		System.out.println("inside auth interceptor");
		Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();
		
		String user = (String) sessionAttributes.get("username");
		
		if(user == null){
			return Action.LOGIN;
		}else{
			return actionInvocation.invoke();
		}
		//return null;
	}

}