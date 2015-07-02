package modules.wechat.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "wx_customer", pkName = "openId")
public class WxCustomer extends Model<WxCustomer> {
	public static final WxCustomer dao = new WxCustomer();

	/**
	 * 分页获取所有客户
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Object getAllCustomer(int pageNumber, int pageSize) {
		return WxCustomer.dao.paginate(pageNumber, pageSize, "select *", "from customer");
	}

	/**
	 * 获取客户
	 * 
	 * @param openid
	 * @return
	 */
	public WxCustomer getCustomer(String openid) {
		return WxCustomer.dao.findById(openid);
	}

	/**
	 * 添加关注
	 * 
	 * @param openid
	 * @param create_id
	 * @return
	 */
	public WxCustomer subscribe(String openid, String create_id) {
		WxCustomer customer = getCustomer(openid);
		if (null == customer) {
			// 没有关注过
			new WxCustomer().set("openId", openid).set("subscribe_flag", "1").set("create_id", create_id).set("create_dt", new Date()).save();
			customer = getCustomer(openid);
		} else {
			// 关注过
			customer.set("subscribe_flag", "1").set("update_id", create_id).set("update_dt", new Date()).update();
		}
		return customer;
	}

	/**
	 * 取消关注
	 * 
	 * @param openid
	 */
	public void unsubscribe(String openid) {
		WxCustomer.dao.findById(openid).set("subscribe_flag", "0").update();
	}

}
