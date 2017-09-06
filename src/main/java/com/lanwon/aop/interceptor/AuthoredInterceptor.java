package com.lanwon.aop.interceptor;



import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;


/****
 * 
 * 1.spring boot拦截器默认有 
	HandlerInterceptorAdapter
	AbstractHandlerMapping
	UserRoleAuthorizationInterceptor
	LocaleChangeInterceptor
	ThemeChangeInterceptor
 * 
 * 
 * **/


public class AuthoredInterceptor  implements HandlerInterceptor{

	private Logger  log=Logger.getLogger(AuthoredInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
      /*  if(handlerMethod.getBean().getClass().isAssignableFrom(ApiResourceController.class)||handlerMethod.getBean().getClass().isAssignableFrom(Swagger2Controller.class)){
        	return true;
        }*/
       
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	  /** 
     * 当出现一个非法令牌时调用 
     */  
    protected boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  
    {  
      
        writeMessageUtf8(response);  //返回值 加参数
        return false;  
    } 

	 private void writeMessageUtf8(HttpServletResponse response) throws IOException  
	    {  
	        try  
	        {  
	            response.setCharacterEncoding("UTF-8");  
	            response.setContentType("text/xml;charset=UTF-8");
	            response.getWriter().print(JSONObject.toJSON(""));  
	        }  
	        finally  
	        {  
	            response.getWriter().close();  
	        }  
	    } 

	

}
