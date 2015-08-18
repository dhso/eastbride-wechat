package modules.codepad.controller;

import java.util.List;

import modules.codepad.kit.TreeKit;
import modules.codepad.model.CodepadModel;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import frame.kit.StringKit;

public class CodepadController extends Controller {

	public void index() {
		render("index.htm");
	}

	public void getListing() {
		String search = getPara("search", "");
		List<Record> listingRecord = CodepadModel.dao.getListing(search);
		if (!StringKit.isNotBlank(search)) {
			listingRecord = TreeKit.formatTree(listingRecord, 0);
		}
		renderJson(listingRecord);
	}

	public void getArticle() {
		Integer id = getParaToInt("id");
		Record articleRecord = CodepadModel.dao.getArticle(id);
		renderJson(articleRecord);
	}

}
