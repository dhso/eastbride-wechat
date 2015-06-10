package model;

import java.util.Date;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

import frame.kit.DateKit;
import frame.kit.IdentityKit;

@SuppressWarnings("serial")
@TableBind(tableName = "customer_wifi", pkName = "open_id")
public class CustomerWifi extends Model<CustomerWifi> {
	public static final CustomerWifi dao = new CustomerWifi();

	/**
	 * 获取客户WIFI信息
	 * 
	 * @param openid
	 * @return
	 */
	public CustomerWifi getCustomerWifi(String openid) {
		return CustomerWifi.dao.findById(openid);
	}

	/**
	 * 申请WIFI密码
	 * 
	 * @param openid
	 * @param create_id
	 * @return
	 */
	public CustomerWifi applyForWifiCaptcha(String openid) {
		CustomerWifi customerWifi = getCustomerWifi(openid);
		String captcha = IdentityKit.randomCaptcha(6);
		Date now = new Date();
		if (null == customerWifi) {
			// 没有申请过
			new CustomerWifi().set("open_id", openid).set("captcha", captcha).set("expired_dt", DateKit.changeDate(now, 12, 30)).save();
			customerWifi = getCustomerWifi(openid);
		} else {
			// 申请过
			customerWifi.set("captcha", captcha).set("expired_dt", DateKit.changeDate(now, 12, 30)).update();
		}
		return customerWifi;
	}
}
