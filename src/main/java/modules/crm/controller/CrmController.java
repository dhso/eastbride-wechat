package modules.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import frame.plugin.shiro.ShiroKit;

public class CrmController extends Controller {
	// 默认登录页面
	@RequiresAuthentication
	public void index() {
		ShiroKit.who();
		render("index.htm");
	}
	
	@RequiresAuthentication
	@ActionKey("crm/menus")
	public void crmMenus() {
		String cascade_id_ = getPara("cascade_id_");
		String app = getPara("app");
		render("wx-menus.htm");
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
