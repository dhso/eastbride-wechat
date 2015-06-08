package frame.sdk.wechat.api;

import frame.sdk.wechat.kit.HttpKit;

/**
 * menu api
 */
public class MenuApi {
	
	private static String getMenu = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
	private static String createMenu = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	
	/**
	 * 查询菜单
	 */
	public static ApiResult getMenu() {
		String jsonResult = HttpKit.get(getMenu + AccessTokenApi.getAccessToken().getAccessToken());
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 创建菜单
	 */
	public static ApiResult createMenu(String jsonStr) {
		String jsonResult = HttpKit.post(createMenu + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
		return new ApiResult(jsonResult);
	}
}


