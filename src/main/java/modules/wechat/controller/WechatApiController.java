package modules.wechat.controller;

import modules.wechat.model.WxPropsModel;
import frame.sdk.wechat.api.ApiConfig;
import frame.sdk.wechat.api.ApiResult;
import frame.sdk.wechat.api.MenuApi;
import frame.sdk.wechat.api.UserApi;
import frame.sdk.wechat.jfinal.ApiController;

public class WechatApiController extends ApiController {

	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的 ApiConfig 对象即可 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
		String appId = getPara();
		WxPropsModel wxProp = WxPropsModel.dao.getProp(appId);
		ApiConfig ac = null;
		if (null != wxProp) {
			// 配置微信 API 相关常量
			// 1：true进行加密且必须配置 encodingAesKey
			// 2：false采用明文模式，同时也支持混合模式
			ac = new ApiConfig(wxProp.getStr("token"), wxProp.getStr("appId"), wxProp.getStr("appSecret"), wxProp.getBoolean("messageEncrypt"), wxProp.getStr("encodingAesKey"));
		}
		return ac;
	}

	/**
	 * 获取公众号菜单
	 */
	public void getMenu() {
		ApiResult apiResult = MenuApi.getMenu();
		if (apiResult.isSucceed())
			renderText(apiResult.getJson());
		else
			renderText(apiResult.getErrorMsg());
	}

	/**
	 * 获取公众号关注用户
	 */
	public void getFollowers() {
		ApiResult apiResult = UserApi.getFollows();
		renderText(apiResult.getJson());
	}

}
