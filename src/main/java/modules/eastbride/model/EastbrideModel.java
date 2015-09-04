package modules.eastbride.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
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
	 * 分页获取轮播
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<EastbrideModel> getCarouselPage(int pageNumber, int pageSize) {
		return EastbrideModel.dao.paginate(pageNumber, pageSize, "select *", "from eastbride_carousel");
	}

	/**
	 * 获取图集
	 * 
	 * @return
	 */
	public List<Record> getGallery() {
		return Db.find("select * from eastbride_gallery");
	}

	/**
	 * 分页获取图集
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getGalleryPage(int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from eastbride_gallery");
	}

}
