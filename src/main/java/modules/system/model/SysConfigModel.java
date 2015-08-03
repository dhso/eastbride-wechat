package modules.system.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

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

	/**
	 * 分页获取配置属性
	 * 
	 * @return
	 */
	public Page<SysConfigModel> getConfigsPage(int pageNumber, int pageSize) {
		return SysConfigModel.dao.paginate(pageNumber, pageSize, "select *", "from sys_config sc left join sys_config_type sct on sct.cfg_type_id = sc.cfg_type_id");
	}

	/**
	 * 通过TYPE分页获取配置属性
	 * 
	 * @param cfg_type_id
	 * @return
	 */
	public Page<SysConfigModel> getConfigsPageByType(int pageNumber, int pageSize, String cfg_type_id) {
		return SysConfigModel.dao.paginate(pageNumber, pageSize, "select *", "from sys_config sc left join sys_config_type sct on sct.cfg_type_id = sc.cfg_type_id where sc.cfg_type_id = ?", cfg_type_id);
	}

	/**
	 * 获取属性
	 * 
	 * @return
	 */
	public List<Record> getConfigTypes() {
		return Db.find("select * from sys_config_type");
	}

	/**
	 * 分页获取属性
	 * 
	 * @return
	 */
	public Page<Record> getConfigTypesPage(int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from sys_config_type");
	}
}
