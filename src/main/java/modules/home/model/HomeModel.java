package modules.home.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "home_carousel", pkName = "crs_id")
public class HomeModel extends Model<HomeModel> {
	public static final HomeModel dao = new HomeModel();

	/**
	 * 获取轮播
	 * 
	 * @return
	 */
	public List<HomeModel> getCarousel() {
		return HomeModel.dao.find("select * from home_carousel");
	}

}
