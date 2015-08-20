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
	public List<Record> getTree(Integer id, String search) {
		if (StringKit.isNotBlank(search)) {
			return Db.find("select id,pid,text,iconCls,state,node,open,create_id,create_dt,update_id,update_dt from codepad_article where node = 0 and (text like ? or article like ?)", "%" + search + "%", "%" + search + "%");
		}
		return Db.find("select id,pid,text,iconCls, state,node,open,create_id,create_dt,update_id,update_dt from codepad_article where pid = ?", id);
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

	/**
	 * 添加文章
	 * 
	 * @param pid
	 * @param text
	 * @param iconCls
	 * @param open
	 * @param article
	 * @param create_id
	 * @return
	 */
	public Record addArticle(Integer pid, String text, String iconCls, Integer open, String article, String create_id) {
		Db.update("insert into codepad_article(pid,text,iconCls,state,node,open,article,create_id,create_dt) values (?,?,?,'open',0,?,?,?,now())", pid, text, iconCls, open, article, create_id);
		return Db.findFirst("select id,pid,text,iconCls, state,node,open,create_id,create_dt,update_id,update_dt from codepad_article where pid= ? and text = ? and iconCls = ? and open=? and article=? and create_id=?", pid, text, iconCls, open, article, create_id);
	}

	/**
	 * 添加文件夹
	 * 
	 * @param pid
	 * @param text
	 * @param iconCls
	 * @param create_id
	 * @return
	 */
	public Record addTree(Integer pid, String text, String iconCls, String create_id) {
		Db.update("insert into codepad_article(pid,text,iconCls,state,node,open,create_id,create_dt) values (?,?,?,'closed',1,1,?,now())", pid, text, iconCls, create_id);
		return Db.findFirst("select id,pid,text,iconCls, state,node,open,create_id,create_dt,update_id,update_dt from codepad_article where pid= ? and text = ? and iconCls = ? and state='closed' and node = 1 and open = 1  and create_id=?", pid, text, iconCls, create_id);
	}

	/**
	 * 删除文件
	 * 
	 * @param id
	 */
	public void delTree(Integer id) {
		Db.update("delete from codepad_article where id = ?", id);
	}
}
