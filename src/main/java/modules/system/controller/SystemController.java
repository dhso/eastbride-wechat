package modules.system.controller;

import java.util.List;

import modules.system.entity.Message;
import modules.system.model.ShiroModel;
import modules.system.model.SysConfigModel;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import frame.plugin.easyui.DataGrid;

public class SystemController extends Controller {
	@RequiresAuthentication
	@ActionKey("system/role")
	public void sysRole() {
		render("system-role.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/role/get")
	public void sysRoleGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> roleList = ShiroModel.dao.getAllRole();
			renderJson(roleList);
		} else {
			Page<Record> rolePage = ShiroModel.dao.getAllRolePage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(rolePage.getTotalRow()), rolePage.getList()));
		}
	}

	@RequiresAuthentication
	@ActionKey("system/role/save")
	@Before(Tx.class)
	public void sysRoleSave() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertRole(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updateRole(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deleteRole(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	@ActionKey("system/permission")
	public void sysPermission() {
		render("system-permission.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/permission/get")
	public void sysPermissionGet() {
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
	@ActionKey("system/permission/save")
	@Before(Tx.class)
	public void sysPermissionSave() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertPermission(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updatePermission(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deletePermission(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	@ActionKey("system/url")
	public void sysUrl() {
		render("system-url.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/url/get")
	public void sysUrlGet() {
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
	@ActionKey("system/url/save")
	@Before(Tx.class)
	public void sysUrlSave() {
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
	@ActionKey("system/url_type")
	public void sysUrlType() {
		render("system-url-type.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/url_type/get")
	public void sysUrlTypeGet() {
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
	@ActionKey("system/url_type/save")
	@Before(Tx.class)
	public void sysUrlTypeSave() {
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

	@RequiresAuthentication
	@ActionKey("system/user")
	public void sysUser() {
		render("system-user.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/user/get")
	public void sysUserGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> userList = ShiroModel.dao.getAllUser();
			renderJson(userList);
		} else {
			Page<Record> userPage = ShiroModel.dao.getAllUserPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(userPage.getTotalRow()), userPage.getList()));
		}

	}

	@RequiresAuthentication
	@ActionKey("system/user/save")
	@Before(Tx.class)
	public void sysUserSave() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertUser(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updateUser(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deleteUser(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	@ActionKey("system/user/create/password")
	public void sysUserCreatePassword() {
		String password = getPara("password", "");
		String salt = getPara("salt", "");
		String newPassword = new SimpleHash("md5", password, ByteSource.Util.bytes(salt), 2).toHex();
		renderText(newPassword);
	}
	
	@RequiresAuthentication
	@ActionKey("system/config")
	public void sysConfig() {
		render("system-config.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/config/get")
	public void sysConfigGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Page<Record> sysConfigs = SysConfigModel.dao.getAllConfigPage(pageNumber, pageSize);
		renderJson(new DataGrid(String.valueOf(sysConfigs.getTotalRow()), sysConfigs.getList()));
	}

	@RequiresAuthentication
	@ActionKey("system/config/save")
	@Before(Tx.class)
	public void sysConfigSave() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			SysConfigModel.dao.insertConfig(insertedJson);
		}
		if (updatedJson.size() > 0) {
			SysConfigModel.dao.updateConfig(updatedJson);
		}
		if (deletedJson.size() > 0) {
			SysConfigModel.dao.deleteConfig(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}
	
	@RequiresAuthentication
	@ActionKey("system/config_type")
	public void sysConfigType() {
		render("system-config-type.htm");
	}
	
	@RequiresAuthentication
	@ActionKey("system/config_type/get")
	public void sysConfigTypeGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> configTypesList = SysConfigModel.dao.getAllConfigType();
			renderJson(configTypesList);
		} else {
			Page<Record> configTypesPage = SysConfigModel.dao.getAllConfigTypePage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(configTypesPage.getTotalRow()), configTypesPage.getList()));
		}
	}
	
	@RequiresAuthentication
	@ActionKey("system/config_type/save")
	@Before(Tx.class)
	public void sysConfigTypeSave() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			SysConfigModel.dao.insertConfigType(insertedJson);
		}
		if (updatedJson.size() > 0) {
			SysConfigModel.dao.updateConfigType(updatedJson);
		}
		if (deletedJson.size() > 0) {
			SysConfigModel.dao.deleteConfigType(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}
}
