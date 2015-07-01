package modules.wechat.model;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "wx_props", pkName = "appId")
public class WxPropsModel extends Model<WxPropsModel> {
	public static final WxPropsModel dao = new WxPropsModel();

	/**
	 * 获取微信配置
	 * 
	 * @param appId
	 * @return
	 */
	public WxPropsModel getProp(String appId) {
		return WxPropsModel.dao.findById(appId);
	}

	/**
	 * 设置微信配置
	 * 
	 * @param appId
	 * @param appSecret
	 * @param token
	 * @param messageEncrypt
	 * @param encodingAesKey
	 * @return
	 */

	public WxPropsModel setProp(String appId, String appSecret, String token, String messageEncrypt, String encodingAesKey) {
		WxPropsModel prop = getProp(appId);
		if (null == prop) {
			new WxPropsModel().set("appId", appId).set("appSecret", appSecret).save();
			prop = getProp(appId);
		} else {
			prop.set("appSecret", appSecret).set("token", token).set("messageEncrypt", messageEncrypt).set("encodingAesKey", encodingAesKey).update();
		}
		return prop;
	}

}
