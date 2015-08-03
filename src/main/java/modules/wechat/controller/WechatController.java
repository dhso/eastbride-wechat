package modules.wechat.controller;

import java.util.List;

import modules.system.model.SysConfigModel;
import modules.wechat.model.CustomerModel;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

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
		Page<CustomerModel> customer = CustomerModel.dao.getAllCustomer(pageNumber, pageSize);
		renderJson(new DataGrid(String.valueOf(customer.getTotalRow()), customer.getList()));
	}

	@RequiresAuthentication
	@ActionKey("weixin/config")
	public void crmWxConfig() {
		render("weixin-config.htm");
	}

	@RequiresAuthentication
	@ActionKey("weixin/config/get")
	public void crmWxConfigGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Page<SysConfigModel> sysConfigs = SysConfigModel.dao.getConfigsPage(pageNumber, pageSize);
		renderJson(new DataGrid(String.valueOf(sysConfigs.getTotalRow()), sysConfigs.getList()));
	}

	@RequiresAuthentication
	@ActionKey("weixin/config_type")
	public void crmWxConfigType() {
		render("weixin-config-type.htm");
	}
	
	@RequiresAuthentication
	@ActionKey("weixin/config_type/get")
	public void crmWxConfigTypeGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> configTypesList = SysConfigModel.dao.getConfigTypes();
			renderJson(configTypesList);
		} else {
			Page<Record> configTypesPage = SysConfigModel.dao.getConfigTypesPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(configTypesPage.getTotalRow()), configTypesPage.getList()));
		}

	}
}
