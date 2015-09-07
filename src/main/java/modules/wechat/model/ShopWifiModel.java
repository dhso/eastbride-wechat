package modules.wechat.model;

import java.util.Date;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Model;

import frame.kit.DateKit;
import frame.kit.IdentityKit;
import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_wifi", pkName = "openId")
public class ShopWifiModel extends Model<ShopWifiModel> {
	public static final ShopWifiModel dao = new ShopWifiModel();

	/**
	 * 获取客户WIFI信息
	 * 
	 * @param openid
	 * @return
	 */
	public ShopWifiModel getShopWifi(String openid) {
		return ShopWifiModel.dao.findById(openid);
	}

	/**
	 * 申请WIFI密码
	 * 
	 * @param openid
	 * @param create_id
	 * @return
	 */
	public ShopWifiModel applyForWifiCaptcha(String openid) {
		ShopWifiModel shopWifi = getShopWifi(openid);
		String captcha = IdentityKit.randomCaptcha(6);
		Date now = new Date();
		if (null == shopWifi) {
			// 没有申请过
			new ShopWifiModel().set("open_id", openid).set("captcha", captcha).set("expired_dt", DateKit.changeDate(now, 12, PropKit.getInt("wifi.captcha"))).save();
			shopWifi = getShopWifi(openid);
		} else {
			// 申请过
			shopWifi.set("captcha", captcha).set("expired_dt", DateKit.changeDate(now, 12, PropKit.getInt("wifi.captcha"))).update();
		}
		return shopWifi;
	}
}
