package modules.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modules.system.entity.Message;
import modules.system.model.ShiroModel;
import modules.system.model.SysConfigModel;
import modules.wechat.model.CustomerModel;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

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
		List<Record> menus = ShiroModel.dao.getUrls(ShiroKit.who());
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

	@RequiresAuthentication
	@ActionKey("crm/sys/permission/get")
	public void crmSysPermission() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> permissionList = ShiroModel.dao.getAllPermission();
			renderJson(permissionList);
		} else {
			Page<Record> permissionPage = ShiroModel.dao.getAllPermissionPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(permissionPage.getTotalRow()), permissionPage.getList()));
		}
	}

	@RequiresAuthentication
	@ActionKey("crm/sys/url")
	public void crmSysUrl() {
		render("sys-url.htm");
	}

	@RequiresAuthentication
	@ActionKey("crm/sys/url/get")
	public void crmSysUrlGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> urlsList = ShiroModel.dao.getAllUrls();
			renderJson(urlsList);
		} else {
			Page<Record> urlsPage = ShiroModel.dao.getAllUrlsPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(urlsPage.getTotalRow()), urlsPage.getList()));
		}
	}

	@RequiresAuthentication
	@ActionKey("crm/sys/url/save")
	@Before(Tx.class)
	public void crmSysUrlSave() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertUrls(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updateUrls(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deleteUrls(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	@ActionKey("crm/sys/url_type")
	public void crmSysUrlType() {
		render("sys-url-type.htm");
	}

	@RequiresAuthentication
	@ActionKey("crm/sys/url_type/get")
	public void crmSysUrlTypeGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> urlsTypeList = ShiroModel.dao.getAllUrlsType();
			renderJson(urlsTypeList);
		} else {
			Page<Record> urlsTypePage = ShiroModel.dao.getAllUrlsTypePage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(urlsTypePage.getTotalRow()), urlsTypePage.getList()));
		}

	}

	@RequiresAuthentication
	@ActionKey("crm/sys/url_type/save")
	@Before(Tx.class)
	public void crmSysUrlTypeSave() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertUrlsType(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updateUrlsType(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deleteUrlsType(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}
}
