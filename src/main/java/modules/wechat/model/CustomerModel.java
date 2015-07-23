package modules.wechat.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "wx_customer", pkName = "openId")
public class CustomerModel extends Model<CustomerModel> {
	public static final CustomerModel dao = new CustomerModel();

	/**
	 * 分页获取所有客户
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<CustomerModel> getAllCustomer(int pageNumber, int pageSize) {
		return CustomerModel.dao.paginate(pageNumber, pageSize, "select *", "from wx_customer");
	}

	/**
	 * 获取客户
	 * 
	 * @param openid
	 * @return
	 */
	public CustomerModel getCustomer(String openid) {
		return CustomerModel.dao.findById(openid);
	}

	/**
	 * 添加关注
	 * 
	 * @param openid
	 * @param create_id
	 * @return
	 */
	public CustomerModel subscribe(String openid, String create_id) {
		CustomerModel customer = getCustomer(openid);
		if (null == customer) {
			// 没有关注过
			new CustomerModel().set("openId", openid).set("subscribe_flag", "1").set("create_id", create_id).set("create_dt", new Date()).save();
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
		CustomerModel.dao.findById(openid).set("subscribe_flag", "0").update();
	}

}
