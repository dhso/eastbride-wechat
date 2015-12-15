package modules.ikea.controller;

import java.util.Map;

import modules.ikea.entity.IkeaUser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.PropKit;

import frame.kit.HttpKit;
import frame.kit.StringKit;

public class SystemController extends Controller {

	// 登录页面
	@ActionKey("/system/login")
	@Before(value = { GET.class })
	public void login() {
		removeSessionAttr("_ikeaUser");
		removeSessionAttr("_source");
		removeSessionAttr("_storecode");
		removeSessionAttr("_giftstorecode");
		removeSessionAttr("_giftstorename");
		render("login.html");
	}

	// 一键登录
	@SuppressWarnings("serial")
	@ActionKey("/system/onekey")
	public void onekey() {
		removeSessionAttr("_ikeaUser");
		removeSessionAttr("_giftstorecode");
		removeSessionAttr("_giftstorename");
		setSessionAttr("_source", "kiosk");
		setSessionAttr("_storecode", getPara("storecode"));
		JSONObject jsonData = new JSONObject() {
			{
				put("authcode", getPara("authcode"));
				put("storecode", getPara("storecode"));
				put("membershipnum", getPara("membershipnum"));
				put("source", (String) getSessionAttr("_source"));
				put("device", (String) getSessionAttr("_template"));
				put("apiKey", PropKit.get("remote.ws.apikey"));
			}
		};
		jsonData.put("signature", StringKit.makeParaSignature(jsonData, PropKit.get("remote.ws.slat"), "apiKey", "authcode"));
		String result = HttpKit.post(PropKit.get("remote.webservice").concat("/ws/customer/kioskLogin"), jsonData.toJSONString(), HttpKit.HTTP_HEADER_JSON);
		JSONObject jsonResult = JSON.parseObject(result);
		if (200 != jsonResult.getInteger("responseCode")) {
			renderError(jsonResult.getInteger("responseCode"));
			return;
		}
		IkeaUser ikeaUser = JSON.parseObject(jsonResult.getString("data"), IkeaUser.class);
		setSessionAttr("_ikeaUser", ikeaUser);
		redirect("/update-customer-info");
	}

	// 登录方法
	@SuppressWarnings("serial")
	@Before(value = { POST.class })
	@ActionKey("/system/login-action")
	public void loginAction() {
		JSONObject jsonData = new JSONObject() {
			{
				put("name", getPara("name"));
				put("mobile", getPara("mobile"));
				put("idcardnum", getPara("idcardnum"));
				put("membershipnum", getPara("membershipnum"));
				put("source", (String) getSessionAttr("_source"));
				put("device", (String) getSessionAttr("_template"));
				put("apiKey", PropKit.get("remote.ws.apikey"));
			}
		};
		jsonData.put("signature", StringKit.makeParaSignature(jsonData, PropKit.get("remote.ws.slat"), "apiKey", "name", "mobile", "idcardnum", "membershipnum"));
		String result = HttpKit.post(PropKit.get("remote.webservice").concat("/ws/customer/customerLogin"), jsonData.toJSONString(), HttpKit.HTTP_HEADER_JSON);
		JSONObject jsonResult = JSON.parseObject(result);
		if (200 != jsonResult.getInteger("responseCode")) {
			renderError(jsonResult.getInteger("responseCode"));
			return;
		}
		IkeaUser ikeaUser = JSON.parseObject(jsonResult.getString("data"), IkeaUser.class);
		setSessionAttr("_ikeaUser", ikeaUser);
		redirect("/update-customer-info");
	}

	// 登出页面
	@ActionKey("/system/logout")
	@Before(value = { GET.class })
	public void logout() {
		removeSessionAttr("_ikeaUser");
		removeSessionAttr("_source");
		removeSessionAttr("_storecode");
		removeSessionAttr("_giftstorecode");
		removeSessionAttr("_giftstorename");
		redirect("/");
	}

	// 错误
	@ActionKey("/system/error")
	public void systemError() {
		String errorCode = getPara();
		String errorDesc = PropKit.get("system.error.code.".concat(errorCode), "未知错误");
		setAttr("errorCode", errorCode);
		setAttr("errorDesc", errorDesc);
		render("system-error.html");
	}

	// 401错误
	@ActionKey("/system/error/401")
	public void systemError401() {
		redirect("/system/login");
	}

	// 404错误
	@ActionKey("/system/error/404")
	public void systemError404() {
		render("system-error-404.html");
	}

	// 500错误
	@ActionKey("/system/error/500")
	public void systemError500() {
		render("system-error-500.html");
	}

	// 60000错误
	@ActionKey("/system/error/60000")
	public void systemError60000() {
		render("system-error-60000.html");
	}

	// 60001错误
	@ActionKey("/system/error/60001")
	public void systemError60001() {
		render("system-error-60001.html");
	}

	// 60002错误
	@ActionKey("/system/error/60002")
	public void systemError60002() {
		render("system-error-60002.html");
	}

	// 60003错误
	@ActionKey("/system/error/60003")
	public void systemError60003() {
		render("system-error-60003.html");
	}

	// 60004错误
	@ActionKey("/system/error/60004")
	public void systemError60004() {
		setAttr("kioskDomain", PropKit.get("kiosk.domain"));
		render("system-error-60004.html");
	}

	// 60022错误
	@ActionKey("/system/error/60022")
	public void systemError60022() {
		render("system-error-60022.html");
	}

	// 60025错误
	@ActionKey("/system/error/60025")
	public void systemError60025() {
		render("system-error-60025.html");
	}

	/**
	 * 远程调用服务
	 * 
	 * _path:'/ws/customer/updateCustomerInfo'
	 * 
	 * __method:'get'
	 * 
	 * _data:'{"data1":"1","data2","2"}'
	 * 
	 * __queryParas:'{"queryParas1":"1","queryParas2","2"}'
	 * 
	 * _type:'json'
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void remoteWS() {
		String result = "";
		String path = PropKit.get("remote.webservice").concat(getPara("_path", ""));
		Map queryParas = (Map) JSON.parseObject(getPara("__queryParas", ""));
		String data = getPara("__data", "");
		String type = getPara("_type", "json");
		if ("get".equalsIgnoreCase(getPara("_method", "get"))) {
			result = HttpKit.get(path, queryParas, HttpKit.HTTP_HEADER_JSON);
		} else {
			result = HttpKit.post(path, queryParas, data, HttpKit.HTTP_HEADER_JSON);
		}
		if ("json".equalsIgnoreCase(type)) {
			renderJson(result);
		} else {
			renderText(result);
		}
	}
}
