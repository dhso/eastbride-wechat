package modules.home.controller;

import modules.home.model.HomeModel;

import com.jfinal.core.Controller;

public class HomeController extends Controller {

	public void index() {
		setAttr("carouselList", HomeModel.dao.getCarousel());
		render("index.htm");
	}

}
