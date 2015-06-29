/**
 * 
 */
/**
 * @author Administrator
 *
 */
package modules.weshop.validator;

import java.util.Arrays;

import modules.system.entity.Config;
import modules.system.entity.Message;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class ShopAdminCheck implements Interceptor {

	public void intercept(ActionInvocation ai) {
		String uid = ai.getController().getPara("aid");
		String shopAdminOpenId = Config.dao.getCfgValue("shop_admin_openid");
		if (Arrays.asList(shopAdminOpenId.split(",")).contains(uid)) {
			ai.invoke();
		} else {
			ai.getController().renderJson(new Message("600", "error", "没有管理权限！"));
			return;
		}
	}
}