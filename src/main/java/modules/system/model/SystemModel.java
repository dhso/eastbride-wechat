package modules.system.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import frame.kit.RecordKit;
import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "system_config", pkName = "cfg_key")
public class SystemModel extends Model<SystemModel> {
	public static final SystemModel dao = new SystemModel();

	/**
	 * 获取所有配置信息
	 * 
	 * @return
	 */
	public List<Record> getAllConfig() {
		return Db.find("select * from system_config");
	}

	/**
	 * 分页获取所有配置信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getAllConfigPage(Integer pageNumber, Integer pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from system_config order by cfg_key asc");
	}

	/**
	 * 批量添加配置
	 * 
	 * @param list
	 * @return
	 */
	public void insertConfig(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("insert into system_config(cfg_key,cfg_val) values (?,?)", "cfg_key,cfg_val", recordList, recordList.size());
	}

	/**
	 * 批量更新配置
	 * 
	 * @param list
	 */
	public void updateConfig(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("update system_config cfg_val= ? where cfg_key = ?", "cfg_val,cfg_key", recordList, recordList.size());
	}

	/**
	 * 批量删除配置
	 * 
	 * @param list
	 */
	public void deleteConfig(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		Db.batch("delete from system_config where cfg_key = ?", "cfg_key", recordList, recordList.size());
	}

	/**
	 * 通过key获取value
	 * 
	 * @param cfg_key
	 * @return
	 */
	public String getConfigVal(String cfg_key) {
		return Db.findFirst("select * from system_config where cfg_key = ?", cfg_key).getStr("cfg_val");
	}
}
