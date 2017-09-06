/**
 * 蓝网科技股份有限公司
 */
package com.lanwon.aop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @author dzb	
 * @date 2017年8月30日 
 * 
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*") })
public class DruidWebStatFilter extends WebStatFilter{
	
	
	public DruidWebStatFilter() {
		System.out.println("druid  Web  Stat  Filter !");
	}
	

}
