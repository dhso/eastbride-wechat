package model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "config", pkName = "cfg_key")
public class Config extends Model<Config> {
	public static final Config dao = new Config();

	/**
	 * 获取配置
	 * 
	 * @param cfg_key
	 * @return
	 */
	public Config getConfig(String cfg_key) {
		return Config.dao.findById(cfg_key);
	}

	/**
	 * 设置配置
	 * 
	 * @param cfg_key
	 * @param cfg_value
	 * @return
	 */
	public Config setConfig(String cfg_key, String cfg_value) {
		Config config = getConfig(cfg_key);
		if (null == config) {
			new Config().set("cfg_key", cfg_key).set("cfg_value", cfg_value).save();
			config = getConfig(cfg_key);
		} else {
			config.set("cfg_value", cfg_value).update();
		}
		return config;
	}

	/**
	 * 获取配置的值
	 * 
	 * @param cfg_key
	 * @return
	 */
	public String getCfgKey(String cfg_key) {
		return getConfig(cfg_key).getStr("cfg_value");
	}
}
