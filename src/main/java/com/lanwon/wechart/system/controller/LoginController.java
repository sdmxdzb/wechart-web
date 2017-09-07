/**
 * 蓝网科技股份有限公司
 */
package com.lanwon.wechart.system.controller;





import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lanwon.aop.shiro.ExtendCaptchaToken;
import com.lanwon.wechart.system.entity.User;
import com.lanwon.wechart.system.service.UserService;





/**
 * @author dzb	
 * @date 2017年9月4日 
 * 
 */
@Controller
@RequestMapping
public class LoginController  {
	
	private static final Logger logger =Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	/***
	 * @see 登录页面
	 * */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String index(Model model){
		return "login";
	}

	
	/**
	 * 默认页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
              return "redirect:/index/login";
        } 
		 String username =user.getUsername();
		if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){  
            logger.info("用户名或密码为空! ");  
            //redirectAttributes.addFlashAttribute("message", "用户名或密码为空!");  
            return "login";  
        }  
		 Subject subject = SecurityUtils.getSubject();
        //验证  
		 ExtendCaptchaToken token = new ExtendCaptchaToken(user.getUsername(), user.getPassword(),false,"","");  
        token.setRememberMe(false);
        try {
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            subject.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            //redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
           // redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            //redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
           // redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
           // redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //获取当前的Subject 
		if (subject.isAuthenticated() || subject.isRemembered()) {
			return "index" ;
		}else{
			token.clear();  
            return "login";  
		}
	}
	
	
	/**
	 * 登出
	 * 
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}
	
}
