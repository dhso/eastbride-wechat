package modules.eastbride.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "eastbride_carousel", pkName = "crs_id")
public class EastbrideModel extends Model<EastbrideModel> {
	public static final EastbrideModel dao = new EastbrideModel();

	/**
	 * 获取轮播
	 * 
	 * @return
	 */
	public List<EastbrideModel> getCarousel() {
		return EastbrideModel.dao.find("select * from eastbride_carousel");
	}

}
