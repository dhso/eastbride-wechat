package modules.crm.controller;

import com.jfinal.core.Controller;

public class CrmController extends Controller {
	// 默认登录页面
	public void index() {
		render("login.htm");
	}

}
