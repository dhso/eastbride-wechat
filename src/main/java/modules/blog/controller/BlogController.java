package modules.blog.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.Controller;

public class BlogController extends Controller {
	// 默认登录页面
	@RequiresAuthentication
	public void index() {
		render("index.htm");
	}


	
}
