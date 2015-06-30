package modules.wechat.entity;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Customer extends Model<Customer> {
	public static final Customer dao = new Customer();

	/**
	 * 分页获取所有客户
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Object getAllCustomer(int pageNumber, int pageSize) {
		return Customer.dao.paginate(pageNumber, pageSize, "select *", "from customer");
	}

	/**
	 * 获取客户
	 * 
	 * @param openid
	 * @return
	 */
	public Customer getCustomer(String openid) {
		return Customer.dao.findById(openid);
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
			new Customer().set("open_id", openid).set("subscribe_flag", "1").set("create_id", create_id).set("create_dt", new Date()).save();
			customer = getCustomer(openid);
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