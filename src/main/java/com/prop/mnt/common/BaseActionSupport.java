package com.prop.mnt.common;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseActionSupport extends ActionSupport {

	/*
     * Gets the request Parameter from Action context
     */
    /**
     * Gets the request parameter.
     * 
     * @param parameter the parameter
     * @return the request parameter
     */
    public String getRequestParameter(String parameter) {
        Map<String, Object> requestMap = ActionContext.getContext().getParameters();
        String reqParam = null;

        String[] reqParams = (String[]) requestMap.get(parameter);
        if (reqParams != null && reqParams.length > 0) {
            reqParam = reqParams[0];
        }

        return reqParam;
    }

}
