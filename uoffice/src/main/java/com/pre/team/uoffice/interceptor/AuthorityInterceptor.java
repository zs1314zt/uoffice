package com.pre.team.uoffice.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.User;

public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	//拦截Action处理的拦截方法
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		//取出名为user的session属性
		User user = (User) session.get(Constants.CURRENT_USER);
		//如果没有登录，则返回登录界面
		if(user!=null){
			return invocation.invoke();
		}
		return Action.LOGIN;
	}

}
