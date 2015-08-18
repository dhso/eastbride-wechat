package modules.codepad.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

import frame.kit.StringKit;
import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "codepad_article", pkName = "id")
public class CodepadModel extends Model<CodepadModel> {
	public static final CodepadModel dao = new CodepadModel();

	/**
	 * 获取目录列表
	 * 
	 * @return
	 */
	public List<Record> getListing(String search) {
		if (StringKit.isNotBlank(search)) {
			return Db.find("select id,pid,text,iconCls,state,node,open,create_id,create_dt,update_id,update_dt from codepad_article where node = 0 and (text like ? or article like ?)", "%" + search + "%", "%" + search + "%");
		}
		return Db.find("select id,pid,text,iconCls,state,node,open,create_id,create_dt,update_id,update_dt from codepad_article");
	}

	/**
	 * 获取文章
	 * 
	 * @param id
	 * @return
	 */
	public Record getArticle(Integer id) {
		return Db.findFirst("select id,text,article,open,create_id,create_dt,update_id,update_dt from codepad_article where id = ?", id);
	}
}
