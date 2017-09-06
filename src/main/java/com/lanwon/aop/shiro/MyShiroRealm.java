package com.lanwon.aop.shiro;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lanwon.aop.shiro.session.SessionConstants;
import com.lanwon.common.exception.CaptchaException;
import com.lanwon.common.util.StringUtils;
import com.lanwon.goole.code.Constants;
import com.lanwon.wechart.base.mapper.JSONMapper;
import com.lanwon.wechart.system.entity.Menu;
import com.lanwon.wechart.system.entity.Role;
import com.lanwon.wechart.system.entity.User;
import com.lanwon.wechart.system.entity.UserRole;
import com.lanwon.wechart.system.service.MenuService;
import com.lanwon.wechart.system.service.RoleService;
import com.lanwon.wechart.system.service.UserService;

/**
 * @see :shiro权限控制. <br/>
 */
@Service
public class MyShiroRealm extends AuthorizingRealm {
	
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

	
	/**  
     * 登录认证  
     */ 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		  //UsernamePasswordToken用于存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken; 
		   logger.info("登录认证!");  
	       logger.info("验证当前Subject时获取到token为：" + 
		   ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE)); 
		 User user = userService.getByUsername(token.getUsername());
		 if (user != null) {
			  	if(user.getStatus() == 0){  
	                throw new DisabledAccountException();  
	            }
	            // ShiroUser shiroUser = new ShiroUser(user.getId(),
	            // user.getUsername(), user.getUsername());
	            this.initSession(user);//设置session用户
	            /**
	             * 记录登录次数、时间
	             */
	            userService.saveLoginTime(user);
	            SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
	                    user.getUsername());
	            return authInfo;
	        } else {
	            return null;
	        }
	}
	
	 /**
     * 设置用户session
     */
    private void initSession(User user) {
        logger.info("sessionTimeOut:" + SessionConstants.sessionTimeOut);
        Session session = SecurityUtils.getSubject().getSession();
        session.setTimeout(SessionConstants.sessionTimeOut);
        session.setAttribute(SessionConstants.SESSION_USER_KEY, user);
    }
   
	
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	 // ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.getByUsername(username);
        Long userId = user.getId();
        logger.debug("user:" + JSONMapper.getInstance().toJson(user));
        logger.debug("principals:" + JSONMapper.getInstance().toJson(SecurityUtils.getSubject().getPrincipals()));
        /**
         * 把principals放session中 key=userId value=principals
         */
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()),
                SecurityUtils.getSubject().getPrincipals());
        // 权限信息对象info，用来存放查出的用户的所有的角色及权限  
         SimpleAuthorizationInfo info = this.authUser(userId);
        return info;
	}
   

    /**
     * 为用户授权.
     * @param userId
     * @return
     */
    private SimpleAuthorizationInfo authUser(Long userId) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /**
         * 赋予角色
         */
        List<UserRole> userRoles = userService.getUserRoles(userId);
        for (UserRole userRole : userRoles) {
            Role role = roleService.getRoleById(userRole.getRoleId());
            info.addRole(role.getCode());
        }
        
        /**
         * 赋予权限
         */
        List<Menu> permissions = menuService.getMenusByUserId(userId);
        for (Menu permission : permissions) {
            if (StringUtils.isNotEmpty(permission.getPermCode()))
                info.addStringPermission(permission.getPermCode());
        }
        
     // 返回null将会导致用户访问任何被拦截的请求时都会自动跳转到unauthorizedUrl指定的地址  
        return info;
    }

    /**
     * 验证码校验.
     *
     * @param token
     * @return boolean
     */
    protected boolean checkVerifyCode(ExtendCaptchaToken token) {
        String captcha = (String) SecurityUtils.getSubject().getSession()
                .getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new CaptchaException("验证码错误！");
        }
        return true;
    }

    /**
     * 设定Password校验.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        /**
         * 自定义密码验证
         */
        setCredentialsMatcher(new CustomizedCredentialsMatcher());
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public Long id;
        public String username;
        public String name;

        public ShiroUser(Long id, String username, String name) {
            this.id = id;
            this.username = username;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return username;
        }

        /**
         * 重载hashCode.
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(username);
        }

        /**
         * 重载equals.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ShiroUser other = (ShiroUser) obj;
            if (username == null) {
                if (other.username != null) {
                    return false;
                }
            } else if (!username.equals(other.username)) {
                return false;
            }
            return true;
        }
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        this.clearAllCachedAuthenticationInfo();
        this.clearAllCachedAuthorizationInfo();
    }

	

	

}
