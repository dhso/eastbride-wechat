package modules.eastbride.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import frame.plugin.easyui.DataGrid;
import modules.eastbride.model.EastbrideModel;

public class EastbrideController extends Controller {

	public void index() {
		setAttr("carouselList", EastbrideModel.dao.getCarousel());
		setAttr("galleryList", EastbrideModel.dao.getGallery());
		render("index.htm");
	}

	@RequiresAuthentication
	@ActionKey("eastbride/carousel/manage")
	public void carouselManage() {
		render("eastbride-carousel.htm");
	}

	@RequiresAuthentication
	@ActionKey("eastbride/carousel/get")
	public void carouselGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Page<EastbrideModel> carousel = EastbrideModel.dao.getCarouselPage(pageNumber, pageSize);
		renderJson(new DataGrid(String.valueOf(carousel.getTotalRow()), carousel.getList()));
	}

	@RequiresAuthentication
	@ActionKey("eastbride/gallery/manage")
	public void galleryManage() {
		render("eastbride-gallery.htm");
	}

	@RequiresAuthentication
	@ActionKey("eastbride/gallery/get")
	public void galleryGet() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Page<Record> gallery = EastbrideModel.dao.getGalleryPage(pageNumber, pageSize);
		renderJson(new DataGrid(String.valueOf(gallery.getTotalRow()), gallery.getList()));
	}

}
