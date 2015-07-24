package modules.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modules.system.model.ShiroModel;
import modules.system.model.SysConfigModel;
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
	@ActionKey("crm/wx/config/get")
	public void crmWxConfigGet() {
		String type = getPara("type");
		List<SysConfigModel> sysConfigs = SysConfigModel.dao.getConfigByType(type);
		ArrayList<Map<String, String>> rowList = new ArrayList<Map<String, String>>();
		Iterator<SysConfigModel> scIt = sysConfigs.iterator();
		while (scIt.hasNext()) {
			SysConfigModel sysConfigModel = scIt.next();
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", String.valueOf(sysConfigModel.get("cfg_key")));
			map.put("value", String.valueOf(sysConfigModel.get("cfg_value")));
			map.put("group", String.valueOf(sysConfigModel.get("cfg_type_name")));
			map.put("editor", "text");
			rowList.add(map);
		}
		renderJson(new DataGrid(String.valueOf(sysConfigs.size()), rowList));
	}

}
