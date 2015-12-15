package modules.wechat.controller;

import modules.wechat.model.WechatCustomerModel;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import frame.plugin.easyui.DataGrid;

public class WechatController extends Controller {
	@RequiresAuthentication
	@ActionKey("weixin/customer")
	public void crmWxCustomer() {
		render("weixin-customer.htm");
	}

	@RequiresAuthentication
	@ActionKey("weixin/customer/get")
	public void crmWxCustomerGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Page<WechatCustomerModel> customer = WechatCustomerModel.dao.getAllCustomer(pageNumber, pageSize);
		renderJson(new DataGrid(String.valueOf(customer.getTotalRow()), customer.getList()));
	}
	
}
