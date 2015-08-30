package modules.eastbride.controller;

import modules.eastbride.model.EastbrideModel;

import com.jfinal.core.Controller;

public class EastbrideController extends Controller {

	public void index() {
		setAttr("carouselList", EastbrideModel.dao.getCarousel());
		render("index.htm");
	}

}
