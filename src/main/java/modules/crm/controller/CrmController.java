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
		setAttr("role", ShiroModel.dao.getRole(ShiroKit.who()).getStr("role_desc"));
		render("index.htm");
	}

	@RequiresAuthentication
	@ActionKey("crm/menus")
	public void crmMenus() {
		List<Record> menus = ShiroModel.dao.getUrls(ShiroKit.who());
		renderJson(menus);
	}

}
