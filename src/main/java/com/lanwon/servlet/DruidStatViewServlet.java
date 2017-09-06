/**
 * 蓝网科技股份有限公司
 */
package com.lanwon.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * @author dzb	
 * @date 2017年8月30日 
 * @see  视图展示 druid
 */
@WebServlet(urlPatterns = { "/druid/*" }, initParams = { @WebInitParam(name = "loginUsername", value = "admin"), @WebInitParam(name = "loginPassword", value = "admin") })
public class DruidStatViewServlet extends StatViewServlet {

	private static final long serialVersionUID = 5025331235411935951L;
	
	

}
