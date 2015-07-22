package modules.crm.controller;

import java.util.List;

import modules.system.model.ShiroModel;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import frame.plugin.shiro.ShiroKit;

public class CrmController extends Controller {
	// 默认登录页面
	@RequiresAuthentication
	public void index() {
		render("index.htm");
	}

	@RequiresAuthentication
	@ActionKey("crm/menus")
	public void crmMenus() {
		List<Record> menus = ShiroModel.dao.getMenus(ShiroKit.who());
		renderJson(menus);
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
