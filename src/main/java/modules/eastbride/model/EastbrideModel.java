package modules.eastbride.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

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

	/**
	 * 获取图集
	 * 
	 * @return
	 */
	public List<Record> getGallery() {
		return Db.find("select * from eastbride_gallery");
	}

}
