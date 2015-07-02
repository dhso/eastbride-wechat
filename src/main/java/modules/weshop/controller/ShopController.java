package modules.weshop.controller;

import java.io.IOException;

import modules.system.entity.Message;
import modules.wechat.model.WxCustomer;
import modules.weshop.entity.ShopGoods;
import modules.weshop.entity.ShopOrder;
import modules.weshop.validator.ShopAdminCheck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;

import frame.kit.StringKit;

public class ShopController extends Controller {

	public void index() {
		String openid = getPara("openid", "");
		String channel = getPara("channel", "");
		if (StringKit.isNotBlank(openid) && StringKit.isNotBlank(channel)) {
			setSessionAttr("openid", openid);
			setSessionAttr("channel", channel);
			//setAttr("shop_menu", ShopMenu.dao.getAllMenu());
			//setAttr("shop_name", Config.dao.getCfgValue("wx.shop.name"));
			//setAttr("shop_notification", Config.dao.getCfgValue("wx.shop.notification"));
			//setAttr("shop_goods", ShopGoods.dao.getAllGoods());
			//setAttr("shop_user", Customer.dao.findById(openid));
			render("front/index.htm");
		} else {
			renderText("打开方式错误，链接来源不正确！");
		}

	}

	@ActionKey("/shop/order/getOrders")
	public void getOrders() {
		String openid = getSessionAttr("openid");
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		if (StringKit.isNotBlank(openid)) {
			renderJson(ShopOrder.dao.getOrderPage(pageNumber, pageSize, openid));
		} else {
			renderJson(new Message("200", "error", "获取用户失败！"));
		}
	}

	@ActionKey("/shop/order/addOrder")
	@Before(Tx.class)
	public void addOrder() throws JsonProcessingException, IOException {
		String openid = getSessionAttr("openid");
		String channel = getSessionAttr("channel");
		String cartData = getPara("cartData", "");
		String totalPrice = getPara("totalPrice", "");
		String username = getPara("userData[0][value]", "");
		String phone = getPara("userData[1][value]", "");
		String payStyle = getPara("userData[2][value]", "");
		String address = getPara("userData[3][value]", "");
		String note = getPara("userData[4][value]", "");
		if (StringKit.isNotBlank(openid)) {
			ShopOrder.dao.addOrder(openid, totalPrice, note, payStyle, cartData, username, phone, address, channel);
			renderJson(new Message("200", "success", "提交订单成功！"));
		} else {
			renderJson(new Message("200", "error", "获取用户失败！"));
		}
	}

	@ActionKey("/shop/order/delOrder")
	@Before(Tx.class)
	public void delOrder() {
		String uid = getSessionAttr("uid");
		String orderId = getPara("orderId");
		ShopOrder.dao.delOrder(uid, orderId);
		renderJson(new Message("200", "success", "删除订单成功！"));
	}

	@ActionKey("/shop/good/fetchGoodDetail")
	public void fetchGoodDetail() {
		int id = getParaToInt("id", -999);
		if (id != -999) {
			renderJson(ShopGoods.dao.fetchGoodsDetail(id));
		} else {
			renderJson(new Message("200", "error", "获取商品失败！"));
		}
	}

	@ActionKey("/shop/order/getToDoOrders")
	@Before(ShopAdminCheck.class)
	public void getToDoOrders() {
		setAttr("toDoOrders", ShopOrder.dao.getToDoOrders());
		render("front/toDoOrders.htm");
	}

	@ActionKey("/shop/order/getToDoOrders/json")
	@Before(ShopAdminCheck.class)
	public void getToDoOrdersJson() {
		renderJson(ShopOrder.dao.getToDoOrders());
	}

	@ActionKey("/shop/order/closeOrder")
	@Before(ShopAdminCheck.class)
	public void closeOrder() {
		String orderId = getPara("orderId");
		if (ShopOrder.dao.closeOrder(orderId) == 0) {
			renderJson(new Message("200", "error", "关闭订单失败！"));
		} else {
			renderJson(new Message("200", "success", "关闭订单成功！"));
		}
	}

	@ActionKey("/shop/order/payOrder")
	@Before(ShopAdminCheck.class)
	public void payOrder() {
		String orderId = getPara("orderId");
		String payStatus = getPara("payStatus");
		if (ShopOrder.dao.payOrder(orderId, payStatus) == 0) {
			renderJson(new Message("200", "error", "设置订单支付状态失败！"));
		} else {
			renderJson(new Message("200", "success", "设置订单支付状态成功！"));
		}
	}

	@ActionKey("/shop/order/dealOrder")
	@Before(ShopAdminCheck.class)
	public void dealOrder() {
		String orderId = getPara("orderId");
		String orderStatus = getPara("orderStatus");
		if (ShopOrder.dao.dealOrder(orderId, orderStatus) == 0) {
			renderJson(new Message("200", "error", "设置订单处理状态失败！"));
		} else {
			renderJson(new Message("200", "success", "设置订单处理状态成功！"));
		}
	}

	@ActionKey("/shop/user/Money")
	@Before(ShopAdminCheck.class)
	public void userMoney() {
		String uid = getPara("aid", "");
		setSessionAttr("aid", uid);
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		setAttr("shop_user", WxCustomer.dao.getAllCustomer(pageNumber, pageSize));
		render("front/userMoney.htm");
	}

}
