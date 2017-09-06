/**
 * 蓝网科技股份有限公司
 */
package com.lanwon.aop.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.log4j.Logger;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;

/**
 * @author dzb	
 * @date 2017年9月6日 
 * @see Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 
 * 通过DispachServlet 来主控制一样。
 *  既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，
 *  所以我们需要定义一系列关于URL的规则和访问权限。
 *
 */
@Configuration
@EnableTransactionManagement  
public class ShiroConfiguration {
	
	 private final Logger logger = Logger.getLogger(ShiroConfiguration.class);  
	  
	    /**  
	     * ShiroFilterFactoryBean 处理拦截资源文件问题。  
	     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在  
	     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager  
	     Filter Chain定义说明  
	     1、一个URL可以配置多个Filter，使用逗号分隔  
	     2、当设置多个过滤器时，全部验证通过，才视为通过  
	     3、部分过滤器可指定参数，如perms，roles  
	     *  
	     */  
	    @Bean  
	    public EhCacheManager getEhCacheManager(){  
	        EhCacheManager ehcacheManager = new EhCacheManager();  
	        ehcacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");  
	        return ehcacheManager;  
	    }  

	   
	    @Bean(name = "myShiroRealm")  
	    public MyShiroRealm myShiroRealm(EhCacheManager ehCacheManager){  
	    	MyShiroRealm realm = new MyShiroRealm();  
	        realm.setCacheManager(ehCacheManager);  
	        return realm;  
	    }  
	  
	    /**
	     * 保证实现了Shiro内部lifecycle函数的bean执行
	     * **/
	    @Bean(name = "lifecycleBeanPostProcessor")  
	    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){  
	        return new LifecycleBeanPostProcessor();  
	    }  
	
	    /**
	     * AOP式方法级权限检查 
	     * **/
	    @Bean  
	    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){  
	        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();  
	        creator.setProxyTargetClass(true);  
	        return creator;  
	    }  
	
	    
	    
	    @Bean(name = "securityManager") 
	    public SecurityManager securityManager(MyShiroRealm realm){
	       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
	       securityManager.setRealm(realm);
	       securityManager.setCacheManager(getEhCacheManager());
	       return securityManager;
	    }
	    
	    
	    
	    @Bean  
	    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){  
	        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();  
	        advisor.setSecurityManager(securityManager);  
	        return advisor;  
	    }  
	    
	 /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
        Filter Chain定义说明
       1、一个URL可以配置多个Filter，使用逗号分隔
       2、当设置多个过滤器时，全部验证通过，才视为通过
       3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean(name = "shiroFilter") 
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
       System.out.println("ShiroConfiguration.shirFilter()  is  start");
       MyShiroFilterFactoryBean shiroFilterFactoryBean  = new MyShiroFilterFactoryBean();
       //设置 SecurityManager(会话管理)
       shiroFilterFactoryBean.setSecurityManager(securityManager);
       //shiroFilterFactoryBean.setFilterChainDefinitions("");
       // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
       shiroFilterFactoryBean.setLoginUrl("/");
       //登录成功后要跳转的链接
       //shiroFilterFactoryBean.setSuccessUrl("/index/main");
       //未授权界面;
       shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
       //拦截器.
       Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
       //所有url都都可以匿名访问;
       filterChainDefinitionMap.put("/static/**", "anon");//匿名
       filterChainDefinitionMap.put("/login", "anon");//
       filterChainDefinitionMap.put("/logout", "logout"); 
       filterChainDefinitionMap.put("/**", "authc");//需要认证才能访问页面
       //配置记住我或认证通过可以访问；
       filterChainDefinitionMap.put("/rest/**", "authcBasic");
       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
       logger.info("shiro拦截器工厂类注入成功"); 
     
       return shiroFilterFactoryBean;
    }
    
    /*1.LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
             负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。主要是AuthorizingRealm类的子类，
            以及EhCacheManager类。  
    2.HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，当然在登陆认证的生活，
                 这个类也负责对form里输入的密码进行编码。  
    3.ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理，可以参考JdbcRealm的实现。  
    4.EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，
            放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。  
    5.SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。  
    6.ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。它主要保持了三项数据，
    securityManager，filters，filterChainDefinitionManager。  
    7.DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。  
    8.AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
                  内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。*/ 
   

}
