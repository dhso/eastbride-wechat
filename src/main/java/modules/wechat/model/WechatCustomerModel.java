package modules.wechat.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "wechat_customer", pkName = "openid")
public class WechatCustomerModel extends Model<WechatCustomerModel> {
	public static final WechatCustomerModel dao = new WechatCustomerModel();

	/**
	 * 分页获取所有客户
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<WechatCustomerModel> getAllCustomer(int pageNumber, int pageSize) {
		return WechatCustomerModel.dao.paginate(pageNumber, pageSize, "select *", "from wx_customer");
	}

	/**
	 * 获取客户
	 * 
	 * @param openid
	 * @return
	 */
	public WechatCustomerModel getCustomer(String openid) {
		return WechatCustomerModel.dao.findById(openid);
	}

	/**
	 * 添加关注
	 * 
	 * @param openid
	 * @param create_id
	 * @return
	 */
	public WechatCustomerModel subscribe(String openid, String create_id) {
		WechatCustomerModel customer = getCustomer(openid);
		if (null == customer) {
			// 没有关注过
			new WechatCustomerModel().set("openId", openid).set("subscribe_flag", "1").set("create_id", create_id).set("create_dt", new Date()).save();
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
		WechatCustomerModel.dao.findById(openid).set("subscribe_flag", "0").update();
	}

}
