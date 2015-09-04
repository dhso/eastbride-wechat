package modules.eastbride.controller;

import modules.eastbride.model.EastbrideModel;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class EastbrideController extends Controller {

	public void index() {
		setAttr("carouselList", EastbrideModel.dao.getCarousel());
		setAttr("galleryList", EastbrideModel.dao.getGallery());
		render("index.htm");
	}
	
	@RequiresAuthentication
	@ActionKey("eastbride/carousel/manage")
	public void carouselManage() {
		render("weixin-customer.htm");
	}

}
