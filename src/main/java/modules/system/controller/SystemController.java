package modules.system.controller;

import java.util.List;

import modules.system.entity.Message;
import modules.system.model.ShiroModel;

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
	public void crmSysRole() {
		render("system-role.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/role/get")
	public void crmSysRoleGet() {
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
	public void crmSysRoleSave() {
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
	public void crmSysPermission() {
		render("system-permission.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/permission/get")
	public void crmSysPermissionGet() {
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
	public void crmSysPermissionSave() {
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
	public void crmSysUrl() {
		render("system-url.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/url/get")
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
	@ActionKey("system/url/save")
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
	@ActionKey("system/url_type")
	public void crmSysUrlType() {
		render("system-url-type.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/url_type/get")
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
	@ActionKey("system/url_type/save")
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

	@RequiresAuthentication
	@ActionKey("system/user")
	public void crmSysUser() {
		render("system-user.htm");
	}

	@RequiresAuthentication
	@ActionKey("system/user/get")
	public void crmSysUserGet() {
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
	public void crmSysUserSave() {
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
	public void crmSysUserCreatePassword() {
		String password = getPara("password", "");
		String salt = getPara("salt", "");
		String newPassword = new SimpleHash("md5", password, ByteSource.Util.bytes(salt), 2).toHex();
		renderText(newPassword);
	}
}
