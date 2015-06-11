package model.shop;

import java.util.Date;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_history", pkName = "history_id")
public class ShopHistory extends Model<ShopHistory> {
	public static final ShopHistory dao = new ShopHistory();

	/**
	 * 分页获取所有历史记录
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<ShopHistory> getAllHistory(int pageNumber, int pageSize) {
		return ShopHistory.dao.paginate(pageNumber, pageSize, "select *", "from shop_history");
	}

	/**
	 * 分页获取历史记录
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param openid
	 * @return
	 */
	public Page<ShopHistory> getHistory(int pageNumber, int pageSize, String openid) {
		return ShopHistory.dao.paginate(pageNumber, pageSize, "select * ", "from shop_history where open_id = ?", openid);
	}

	/**
	 * 添加历史记录
	 * 
	 * @param openid
	 * @param event_desc
	 * @param create_id
	 * @return
	 */
	public boolean addHistory(String openid, String event_desc, String create_id) {
		return new ShopHistory().set("open_id", openid).set("event_desc", event_desc).set("create_id", create_id).set("create_dt", new Date()).save();
	}
}
