package modules.system.model;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "sys_config", pkName = "cfg_key")
public class SysConfigModel extends Model<SysConfigModel> {
	public static final SysConfigModel dao = new SysConfigModel();

	/**
	 * 获取配置
	 * 
	 * @param cfg_key
	 * @return
	 */
	public SysConfigModel getConfig(String cfg_key) {
		return SysConfigModel.dao.findById(cfg_key);
	}

	/**
	 * 设置配置
	 * 
	 * @param cfg_key
	 * @param cfg_value
	 * @return
	 */
	public SysConfigModel setConfig(String cfg_key, String cfg_value) {
		SysConfigModel config = getConfig(cfg_key);
		if (null == config) {
			new SysConfigModel().set("cfg_key", cfg_key).set("cfg_value", cfg_value).save();
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
	public String getCfgValue(String cfg_key) {
		return getConfig(cfg_key).getStr("cfg_value");
	}
}
