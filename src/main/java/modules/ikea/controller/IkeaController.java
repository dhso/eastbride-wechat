package modules.ikea.controller;

import java.util.ArrayList;

import modules.ikea.entity.IkeaUser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

import frame.interceptor.AuthInterceptor;
import frame.kit.HttpKit;
import frame.kit.StringKit;
import frame.plugin.memcached.MemCacheKit;

public class IkeaController extends Controller {

	@ActionKey("/")
	public void index() {
		removeSessionAttr("_ikeaUser");
		removeSessionAttr("_giftstorecode");
		removeSessionAttr("_giftstorename");
		render("index.html");
	}

	@ActionKey("/memcached/put")
	public void memcachedPut() {
		String key = getPara(0);
		String value = getPara(1);
		MemCacheKit.put(key, value);
		renderText("success");
	}

	@ActionKey("/memcached/get")
	public void memcachedGet() {
		String key = getPara();
		String value = (String) MemCacheKit.get(key);
		renderText(value);
	}

	@ActionKey("/kiosk")
	public void kiosk() {
		redirect(PropKit.get("kiosk.domain").concat("?store=").concat((String) getSessionAttr("_storecode")));
		removeSessionAttr("_ikeaUser");
		removeSessionAttr("_source");
		removeSessionAttr("_storecode");
		removeSessionAttr("_giftstorecode");
		removeSessionAttr("_giftstorename");
	}

	@ActionKey("/activity-rules")
	public void activityRules() {
		render("activity-rules.html");
	}

	@SuppressWarnings("serial")
	@ActionKey("/update-customer-info")
	@Before(value = { AuthInterceptor.class })
	public void updateCustomerInfo() {
		JSONObject jsonData = new JSONObject() {
			{
				put("source", (String) getSessionAttr("_source"));
				put("device", (String) getSessionAttr("_template"));
				put("apiKey", PropKit.get("remote.ws.apikey"));
			}
		};
		jsonData.put("signature", StringKit.makeParaSignature(jsonData, PropKit.get("remote.ws.slat"), "apiKey"));
		String result = HttpKit.post(PropKit.get("remote.webservice").concat("/ws/customer/getCounters"), jsonData.toJSONString(), HttpKit.HTTP_HEADER_JSON);
		JSONObject jsonResult = JSON.parseObject(result);
		if (200 != jsonResult.getInteger("responseCode")) {
			renderError(jsonResult.getInteger("responseCode"));
			return;
		}
		setAttr("counters", jsonResult.get("data"));
		render("update-customer-info.html");
	}

	@SuppressWarnings({ "serial" })
	@ActionKey("/update-customer-info-action")
	@Before(value = { AuthInterceptor.class })
	public void updateCustomerInfoAction() {
		setSessionAttr("_giftstorecode", getPara("storecode"));
		setSessionAttr("_giftstorename", getPara("storename"));
		JSONObject jsonData = new JSONObject() {
			{
				put("membershipnum", ((IkeaUser) getSessionAttr("_ikeaUser")).getMmbMembershipnum());
				put("marrigestatus", getPara("marrigestatus"));
				if ("1".equalsIgnoreCase(getPara("has-little-child"))) {
					put("childrenstatus", new JSONArray(new ArrayList<Object>() {
						{
							if (StringKit.isNotBlank(getPara("child1"))) {
								add(new JSONObject() {
									{
										put("child", getPara("child1_year", "1990") + "-" + getPara("child1_month", "1") + "-1");
									}
								});
							}
							if (StringKit.isNotBlank(getPara("child2"))) {
								add(new JSONObject() {
									{
										put("child", getPara("child2_year", "1990") + "-" + getPara("child2_month", "1") + "-1");
									}
								});
							}
						}
					}));
				}
				put("renovationtime", getPara("renovationtime"));
				put("period", ((IkeaUser) getSessionAttr("_ikeaUser")).getPeriod());
				put("storecode", getPara("storecode"));
				put("source", (String) getSessionAttr("_source"));
				put("device", (String) getSessionAttr("_template"));
				put("apiKey", PropKit.get("remote.ws.apikey"));
				put("cstId", ((IkeaUser) getSessionAttr("_ikeaUser")).getCstId());
			}
		};
		jsonData.put("signature", StringKit.makeParaSignature(jsonData, PropKit.get("remote.ws.slat"), "apiKey", "membershipnum"));
		String result = HttpKit.post(PropKit.get("remote.webservice").concat("/ws/customer/updateCustomerInfo"), jsonData.toJSONString(), HttpKit.HTTP_HEADER_JSON);
		JSONObject jsonResult = JSON.parseObject(result);
		if (200 != jsonResult.getInteger("responseCode")) {
			renderError(jsonResult.getInteger("responseCode"));
			return;
		}
		redirect("/show-store-gifts");
	}

	@SuppressWarnings("serial")
	@ActionKey("/show-store-gifts")
	@Before(value = { AuthInterceptor.class })
	public void showStoreGifts() {
		JSONObject jsonData = new JSONObject() {
			{
				put("membershipnum", ((IkeaUser) getSessionAttr("_ikeaUser")).getMmbMembershipnum());
				put("storecode", (String) getSessionAttr("_giftstorecode"));
				put("giftcount", "");
				put("rewardtype", ((IkeaUser) getSessionAttr("_ikeaUser")).getRewardtype());
				put("period", ((IkeaUser) getSessionAttr("_ikeaUser")).getPeriod());
				put("source", (String) getSessionAttr("_source"));
				put("device", (String) getSessionAttr("_template"));
				put("apiKey", PropKit.get("remote.ws.apikey"));
			}
		};
		jsonData.put("signature", StringKit.makeParaSignature(jsonData, PropKit.get("remote.ws.slat"), "apiKey", "membershipnum"));
		String result = HttpKit.post(PropKit.get("remote.webservice").concat("/ws/gift/showStoreGifts"), jsonData.toJSONString(), HttpKit.HTTP_HEADER_JSON);
		JSONObject jsonResult = JSON.parseObject(result);
		if (200 != jsonResult.getInteger("responseCode")) {
			renderError(jsonResult.getInteger("responseCode"));
			return;
		}
		setAttr("gifts", jsonResult.get("data"));
		render("show-store-gifts.html");
	}

	@SuppressWarnings("serial")
	@ActionKey("/choose-gift")
	@Before(value = { AuthInterceptor.class })
	public void chooseGift() {
		JSONObject jsonData = new JSONObject() {
			{
				put("membershipnum", ((IkeaUser) getSessionAttr("_ikeaUser")).getMmbMembershipnum());
				put("storecode", (String) getSessionAttr("_giftstorecode"));
				put("giftid", getPara("giftid"));
				put("mobile", ((IkeaUser) getSessionAttr("_ikeaUser")).getCstMobile());
				put("period", ((IkeaUser) getSessionAttr("_ikeaUser")).getPeriod());
				put("rewardtype", ((IkeaUser) getSessionAttr("_ikeaUser")).getRewardtype());
				put("source", (String) getSessionAttr("_source"));
				put("device", (String) getSessionAttr("_template"));
				put("apiKey", PropKit.get("remote.ws.apikey"));
			}
		};
		jsonData.put("signature", StringKit.makeParaSignature(jsonData, PropKit.get("remote.ws.slat"), "apiKey", "membershipnum"));
		String result = HttpKit.post(PropKit.get("remote.webservice").concat("/ws/gift/chooseGift"), jsonData.toJSONString(), HttpKit.HTTP_HEADER_JSON);
		JSONObject jsonResult = JSON.parseObject(result);
		if (200 != jsonResult.getInteger("responseCode")) {
			renderError(jsonResult.getInteger("responseCode"));
			return;
		}
		setAttr("result", jsonResult);
		render("choose-gift-result.html");
	}

	@SuppressWarnings("serial")
	@ActionKey("/print-gift-coupon")
	@Before(value = { AuthInterceptor.class })
	public void printGiftCoupon() {
		JSONObject jsonData = new JSONObject() {
			{
				put("membershipnum", ((IkeaUser) getSessionAttr("_ikeaUser")).getMmbMembershipnum());
				put("storecode", (String) getSessionAttr("_giftstorecode"));
				put("coupid", getPara("coupid"));
				put("period", ((IkeaUser) getSessionAttr("_ikeaUser")).getPeriod());
				put("source", (String) getSessionAttr("_source"));
				put("device", (String) getSessionAttr("_template"));
				put("apiKey", PropKit.get("remote.ws.apikey"));
			}
		};
		jsonData.put("signature", StringKit.makeParaSignature(jsonData, PropKit.get("remote.ws.slat"), "apiKey", "membershipnum"));
		String result = HttpKit.post(PropKit.get("remote.webservice").concat("/ws/gift/printGiftCoupon"), jsonData.toJSONString(), HttpKit.HTTP_HEADER_JSON);
		renderJson(JSON.parseObject(result));
	}
}
