package model;

import java.util.Date;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "customer", pkName = "open_id")
public class Customer extends Model<Customer> {
	public static final Customer dao = new Customer();

	/**
	 * 获取客户
	 * 
	 * @param openid
	 * @return
	 */
	protected Customer getCustomer(String openid) {
		return Customer.dao.findById(openid);
	}

	/**
	 * 添加客户
	 * 
	 * @param openid
	 * @param subscribe_flag
	 * @param create_id
	 * @return
	 */
	protected Boolean addCustomer(String openid, String subscribe_flag, String create_id) {
		return new Customer().set("open_id", openid).set("subscribe_flag", subscribe_flag).set("create_id", create_id).set("create_dt", new Date()).save();
	}

	/**
	 * 添加关注
	 * 
	 * @param openid
	 * @param create_id
	 * @return
	 */
	public Customer subscribe(String openid, String create_id) {
		Customer customer = getCustomer(openid);
		if (null == customer) {
			// 没有关注过
			addCustomer(openid, "1", create_id);
		} else {
			// 关注过
			customer.set("subscribe_flag", "1").set("create_id", create_id).update();
		}
		return customer;
	}

	/**
	 * 取消关注
	 * 
	 * @param openid
	 */
	public void unsubscribe(String openid) {
		Customer.dao.findById(openid).set("subscribe_flag", "0").update();
	}
}
