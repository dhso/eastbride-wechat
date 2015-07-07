package modules.crm.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class CrmController extends Controller {
	// 默认登录页面
	@RequiresAuthentication
	public void index() {
		render("index.htm");
	}
	
	@RequiresAuthentication
	public void home() {
		render("home.htm");
	}
	
	@RequiresAuthentication
	@ActionKey("crm/wx/customer")
	public void wxCustomer() {
		render("wx-customer.htm");
	}
	
	@RequiresAuthentication
	@ActionKey("crm/wx/menus")
	public void wxMenus() {
		render("wx-menus.htm");
	}

}
