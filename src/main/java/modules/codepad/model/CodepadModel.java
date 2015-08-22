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
		Db.update("insert into codepad_article(pid,text,iconCls,state,node,open,article,create_id,create_dt,update_id,update_dt) values (?,?,?,'open',0,?,?,?,now(),?,now())", pid, text, iconCls, open, article, create_id, create_id);
		return Db.findFirst("select id,pid,text,iconCls, state,node,open,create_id,create_dt,update_id,update_dt from codepad_article where pid= ? and text = ? and iconCls = ? and open=? and article=? and create_id=?", pid, text, iconCls, open, article, create_id);
	}

	/**
	 * 更新文章
	 * 
	 * @param id
	 * @param text
	 * @param iconCls
	 * @param open
	 * @param article
	 * @param update_id
	 */
	public void updateArticle(Integer id, String text, String iconCls, Integer open, String article, String update_id) {
		Db.update("update codepad_article set text = ?, iconCls = ?, open = ?, article = ?, update_id = ?, update_dt = now() where id = ?", text, iconCls, open, article, update_id, id);
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
		Db.update("insert into codepad_article(pid,text,iconCls,state,node,open,create_id,create_dt,update_id,update_dt) values (?,?,?,'closed',1,1,?,now(),?,now())", pid, text, iconCls, create_id, create_id);
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

	/**
	 * 更新节点
	 * 
	 * @param id
	 * @param pid
	 * @return
	 */
	public int updateTree(Integer id, Integer pid, String text, String update_id) {
		if (pid != -1) {
			return Db.update("update codepad_article set pid = ?,update_id = ?,update_dt = now() where id = ?", pid, update_id, id);
		}
		if (StringKit.isNotBlank(text)) {
			return Db.update("update codepad_article set text = ?,update_id = ?,update_dt = now() where id = ?", text, update_id, id);
		}
		return 0;
	}
}
