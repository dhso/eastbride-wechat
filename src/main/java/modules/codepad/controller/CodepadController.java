package modules.codepad.controller;

import java.util.List;

import modules.codepad.kit.TreeKit;
import modules.codepad.model.CodepadModel;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

public class CodepadController extends Controller {

	public void index() {
		render("index.htm");
	}

	public void getListing() {
		List<Record> listingRecord = CodepadModel.dao.getListing();
		List<Record> listingFormat = TreeKit.formatTree(listingRecord, 0);
		renderJson(listingFormat);
	}

}
