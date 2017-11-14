package com.cliffhangout.webapp.interceptors;

import com.cliffhangout.beans.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;

public class AuthenticationInterceptor implements Interceptor {

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        if(session != null && session.containsKey("sessionUser"))
        {
            User user = (User) session.get("sessionUser");
            if(user==null)
            {
                return Action.LOGIN;
            }else{
                Action action = (Action) actionInvocation.getAction();
                return actionInvocation.invoke();
            }
        }else{
            return Action.LOGIN;
        }
    }
}
