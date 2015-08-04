package modules.doc.controller;

import java.util.List;

import modules.system.model.ShiroModel;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import frame.plugin.shiro.ShiroKit;

public class DocController extends Controller {
	// 默认登录页面
	@RequiresAuthentication
	public void index() {
		render("index.htm");
	}
	
}
