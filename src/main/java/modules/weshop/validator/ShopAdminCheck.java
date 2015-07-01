/**
 * 
 */
/**
 * @author Administrator
 *
 */
package modules.weshop.validator;

import java.util.Arrays;

import modules.system.entity.Message;
import modules.system.model.Config;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ShopAdminCheck implements Interceptor {

	public void intercept(Invocation inv) {
		String uid = inv.getController().getPara("aid");
		String shopAdminOpenId = Config.dao.getCfgValue("shop_admin_openid");
		if (Arrays.asList(shopAdminOpenId.split(",")).contains(uid)) {
			inv.invoke();
		} else {
			inv.getController().renderJson(new Message("600", "error", "没有管理权限！"));
			return;
		}
	}
}