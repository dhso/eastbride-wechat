package modules.system.controller;

import modules.system.validator.SigninValidator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class SystemController extends Controller {

	// 登录Action
	@Before(SigninValidator.class)
	public void signin() {
		if ("GET".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
			render("login.html");
		} else if ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
			String username = getPara("username");
			String password = getPara("password");
			Boolean rememberMe = "on".equalsIgnoreCase(getPara("rememberMe", "off"));
			render("login.html");
		}
	}

	// 登出Action
	public void signout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		redirect(getCookie("_redrictUrl", "/"));
	}

	public void err401() {
		setAttr("msg", "401 Unauthorized");
		setAttr("success", false);
		renderJson();
	}

	public void err403() {
		setAttr("msg", "403 Forbidden");
		setAttr("success", false);
		renderJson();
	}

	public void err404() {
		render("error/404.htm");
	}

	public void err500() {
		render("error/500.htm");
	}
}
