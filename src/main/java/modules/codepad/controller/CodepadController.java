package modules.codepad.controller;

import java.util.List;

import modules.codepad.entity.Result;
import modules.codepad.model.CodepadModel;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

public class CodepadController extends Controller {

	public void index() {
		render("index.htm");
	}

	public void getTree() {
		String search = getPara("search", "");
		Integer id = getParaToInt("id", 0);
		List<Record> treeRecord = CodepadModel.dao.getTree(id, search);
		renderJson(treeRecord);
	}

	public void getArticle() {
		Integer id = getParaToInt("id");
		Record articleRecord = CodepadModel.dao.getArticle(id);
		renderJson(articleRecord);
	}

	public void addArticle() {
		Integer pid = getParaToInt("pid");
		String text = getPara("text");
		String iconCls = getPara("iconCls");
		Integer open = getParaToInt("open");
		String article = getPara("article");
		String create_id = getPara("create_id");
		Record articleRecord = CodepadModel.dao.addArticle(pid, text, iconCls, open, article, create_id);
		renderJson(new Result("200", articleRecord));
	}

	public void updateArticle() {
		Integer id = getParaToInt("id");
		String text = getPara("text");
		String iconCls = getPara("iconCls");
		Integer open = getParaToInt("open");
		String article = getPara("article");
		String update_id = getPara("update_id");
		CodepadModel.dao.updateArticle(id, text, iconCls, open, article, update_id);
		renderJson(new Result("200", "更新成功！"));
	}

	public void addTree() {
		Integer pid = getParaToInt("pid");
		String text = getPara("text");
		String iconCls = getPara("iconCls");
		String create_id = getPara("create_id");
		Record treeRecord = CodepadModel.dao.addTree(pid, text, iconCls, create_id);
		renderJson(new Result("200", treeRecord));
	}

	public void delTree() {
		Integer id = getParaToInt("id");
		CodepadModel.dao.delTree(id);
		renderJson(new Result("200", "删除成功！"));
	}

	public void updateTree() {
		Integer id = getParaToInt("id");
		Integer pid = getParaToInt("pid", -1);
		String text = getPara("text", "");
		String update_id = getPara("update_id");
		Integer resCount = CodepadModel.dao.updateTree(id, pid, text, update_id);
		if (resCount > 0) {
			renderJson(new Result("200", "更新成功！"));
		} else {
			renderJson(new Result("500", "更新失败！"));
		}

	}

}
