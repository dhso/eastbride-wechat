package modules.wechat.model;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "wx_dev_config", pkName = "appId")
public class WxDevConfigModel extends Model<WxDevConfigModel> {
	public static final WxDevConfigModel dao = new WxDevConfigModel();

	/**
	 * 获取微信配置
	 * 
	 * @param appId
	 * @return
	 */
	public WxDevConfigModel getConfig(String appId) {
		return WxDevConfigModel.dao.findById(appId);
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

	public WxDevConfigModel setConfig(String appId, String appSecret, String token, String messageEncrypt, String encodingAesKey) {
		WxDevConfigModel config = getConfig(appId);
		if (null == config) {
			new WxDevConfigModel().set("appId", appId).set("appSecret", appSecret).save();
			config = getConfig(appId);
		} else {
			config.set("appSecret", appSecret).set("token", token).set("messageEncrypt", messageEncrypt).set("encodingAesKey", encodingAesKey).update();
		}
		return config;
	}

}
