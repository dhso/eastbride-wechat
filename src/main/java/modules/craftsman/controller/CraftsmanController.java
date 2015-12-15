package modules.craftsman.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class CraftsmanController extends Controller {

	@ActionKey("/craftsman/index")
	public void index() {
		render("index.html");
	}

}
