package modules.eastbride.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import frame.kit.RecordKit;
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
	 * 批量插入轮播
	 * 
	 * @param list
	 * @return
	 */
	public int[] insertCarousel(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("insert into eastbride_carousel(crs_h2,crs_p,crs_img,crs_href,crs_button) values (?,?,?,?,?)", "crs_h2,crs_p,crs_img,crs_href,crs_button", recordList, recordList.size());
	}

	/**
	 * 批量更新轮播
	 * 
	 * @param list
	 * @return
	 */
	public int[] updateCarousel(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("update eastbride_carousel set crs_h2 = ?,crs_p= ?,crs_img=?,crs_href=?,crs_button=? where crs_id = ?", "crs_h2,crs_p,crs_img,crs_href,crs_button,crs_id", recordList, recordList.size());
	}

	/**
	 * 批量删除轮播
	 * 
	 * @param list
	 * @return
	 */
	public int[] deleteCarousel(List<?> list) {
		List<Record> recordList = RecordKit.list2RecordList(list);
		return Db.batch("delete from eastbride_carousel where crs_id = ?", "crs_id", recordList, recordList.size());
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
