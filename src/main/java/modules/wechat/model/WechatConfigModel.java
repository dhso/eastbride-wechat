package modules.wechat.model;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "wx_config", pkName = "cfg_key")
public class WechatConfigModel extends Model<WechatConfigModel> {
	public static final WechatConfigModel dao = new WechatConfigModel();

	/**
	 * 获取微信配置
	 * 
	 * @param cfgKey
	 * @return
	 */
	public WechatConfigModel getConfig(String cfgKey) {
		return WechatConfigModel.dao.findById(cfgKey);
	}

	/**
	 * 获取微信配置值
	 * 
	 * @param cfgKey
	 * @return
	 */
	public String getStrValue(String cfgKey) {
		WechatConfigModel config = getConfig(cfgKey);
		if (null != config) {
			return config.getStr("cfg_value");
		} else {
			return null;
		}

	}

	/**
	 * 获取微信配置值
	 * 
	 * @param cfgKey
	 * @return
	 */
	public Integer getIntValue(String cfgKey) {
		WechatConfigModel config = getConfig(cfgKey);
		if (null != config) {
			return config.getInt("cfg_value");
		} else {
			return null;
		}
	}

	/**
	 * 获取微信配置值
	 * 
	 * @param cfgKey
	 * @return
	 */
	public Boolean getBooleanValue(String cfgKey) {
		WechatConfigModel config = getConfig(cfgKey);
		if (null != config) {
			String cfgValue = config.getStr("cfg_value");
			if ("1".equals(cfgValue) || "true".equals(cfgValue)) {
				return true;
			} else {
				return false;
			}
		} else {
			return null;
		}
	}

	/**
	 * 设置微信配置
	 * 
	 * @param cfgKey
	 * @param cfgValue
	 * @return
	 */

	public WechatConfigModel setConfig(String cfgKey, String cfgValue) {
		WechatConfigModel config = getConfig(cfgKey);
		if (null == config) {
			new WechatConfigModel().set("cfg_key", cfgKey).set("cfg_value", cfgValue).save();
			config = getConfig(cfgKey);
		} else {
			config.set("cfg_value", cfgValue).update();
		}
		return config;
	}

}
