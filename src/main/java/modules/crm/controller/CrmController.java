package modules.crm.controller;

import java.util.List;

import modules.system.model.ShiroModel;
import modules.wechat.model.CustomerModel;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import frame.plugin.easyui.DataGrid;
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
	@ActionKey("crm/wx/customer")
	public void crmWxCustomer() {
		render("wx-customer.htm");
	}

	@RequiresAuthentication
	@ActionKey("crm/wx/customer/get")
	public void crmWxCustomerGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Page<CustomerModel> customer = CustomerModel.dao.getAllCustomer(pageNumber, pageSize);
		renderJson(new DataGrid(String.valueOf(customer.getTotalRow()), customer.getList()));
	}

	@RequiresAuthentication
	@ActionKey("crm/wx/config")
	public void crmWxConfig() {
		render("wx-config.htm");
	}
	
	@RequiresAuthentication
	public void home() {
		render("home.htm");
	}

}
