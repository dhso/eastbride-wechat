package modules.wechat.controller;

import modules.wechat.model.WechatConfigModel;
import frame.sdk.wechat.api.ApiConfig;
import frame.sdk.wechat.api.ApiResult;
import frame.sdk.wechat.api.CallbackIpApi;
import frame.sdk.wechat.api.CustomServiceApi;
import frame.sdk.wechat.api.MenuApi;
import frame.sdk.wechat.api.QrcodeApi;
import frame.sdk.wechat.api.ShorturlApi;
import frame.sdk.wechat.api.TemplateMsgApi;
import frame.sdk.wechat.api.UserApi;
import frame.sdk.wechat.jfinal.ApiController;

public class WechatApiController extends ApiController {

	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的 ApiConfig 对象即可 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
		String appId = WechatConfigModel.dao.getStrValue("appId");
		String token = WechatConfigModel.dao.getStrValue("token");
		String appSecret = WechatConfigModel.dao.getStrValue("appSecret");
		Boolean messageEncrypt = WechatConfigModel.dao.getBooleanValue("messageEncrypt");
		String encodingAesKey = WechatConfigModel.dao.getStr("encodingAesKey");
		return new ApiConfig(token, appId, appSecret, messageEncrypt, encodingAesKey);
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
	 * 创建菜单
	 */
	public void createMenu() {
		String str = "{\n" + "    \"button\": [\n" + "        {\n" + "            \"name\": \"进入理财\",\n" + "            \"url\": \"http://m.bajie8.com/bajie/enter\",\n" + "            \"type\": \"view\"\n" + "        },\n" + "        {\n" + "            \"name\": \"安全保障\",\n"
				+ "            \"key\": \"112\",\n" + "\t    \"type\": \"click\"\n" + "        },\n" + "        {\n" + "\t    \"name\": \"使用帮助\",\n" + "\t    \"url\": \"http://m.bajie8.com/footer/cjwt\",\n" + "\t    \"type\": \"view\"\n" + "        }\n" + "    ]\n" + "}";
		ApiResult apiResult = MenuApi.createMenu(str);
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

	/**
	 * 获取用户信息
	 */
	public void getUserInfo() {
		ApiResult apiResult = UserApi.getUserInfo("ohbweuNYB_heu_buiBWZtwgi4xzU");
		renderText(apiResult.getJson());
	}

	/**
	 * 发送模板消息
	 */
	public void sendMsg() {
		String str = " {\n" + "           \"touser\":\"ohbweuNYB_heu_buiBWZtwgi4xzU\",\n" + "           \"template_id\":\"9SIa8ph1403NEM3qk3z9-go-p4kBMeh-HGepQZVdA7w\",\n" + "           \"url\":\"http://www.sina.com\",\n" + "           \"topcolor\":\"#FF0000\",\n" + "           \"data\":{\n"
				+ "                   \"first\": {\n" + "                       \"value\":\"恭喜你购买成功！\",\n" + "                       \"color\":\"#173177\"\n" + "                   },\n" + "                   \"keyword1\":{\n" + "                       \"value\":\"去哪儿网发的酒店红包（1个）\",\n"
				+ "                       \"color\":\"#173177\"\n" + "                   },\n" + "                   \"keyword2\":{\n" + "                       \"value\":\"1元\",\n" + "                       \"color\":\"#173177\"\n" + "                   },\n" + "                   \"remark\":{\n"
				+ "                       \"value\":\"欢迎再次购买！\",\n" + "                       \"color\":\"#173177\"\n" + "                   }\n" + "           }\n" + "       }";
		ApiResult apiResult = TemplateMsgApi.send(str);
		renderText(apiResult.getJson());
	}

	/**
	 * 获取参数二维码
	 */
	public void getQrcode() {
		String str = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}";
		ApiResult apiResult = QrcodeApi.create(str);
		renderText(apiResult.getJson());

		// String str = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"123\"}}}";

		// ApiResult apiResult = QrcodeApi.create(str);

		// renderText(apiResult.getJson());

	}

	/**
	 * 长链接转成短链接
	 */
	public void getShorturl() {
		String str = "{\"action\":\"long2short\"," + "\"long_url\":\"http://wap.koudaitong.com/v2/showcase/goods?alias=128wi9shh&spm=h56083&redirect_count=1\"}";
		ApiResult apiResult = ShorturlApi.getShorturl(str);
		renderText(apiResult.getJson());
	}

	/**
	 * 获取客服聊天记录
	 */
	public void getRecord() {
		String str = "{\n" + "    \"endtime\" : 987654321,\n" + "    \"pageindex\" : 1,\n" + "    \"pagesize\" : 10,\n" + "    \"starttime\" : 123456789\n" + " }";
		ApiResult apiResult = CustomServiceApi.getRecord(str);
		renderText(apiResult.getJson());
	}

	/**
	 * 获取微信服务器IP地址
	 */
	public void getCallbackIp() {
		ApiResult apiResult = CallbackIpApi.getCallbackIp();
		renderText(apiResult.getJson());
	}
}
