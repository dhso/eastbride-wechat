package modules.wechat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modules.system.model.SysConfigModel;
import modules.wechat.model.CustomerModel;

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
