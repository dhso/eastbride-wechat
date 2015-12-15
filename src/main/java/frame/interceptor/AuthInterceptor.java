package frame.interceptor;

import java.util.ArrayList;
import java.util.List;

import modules.ikea.entity.IkeaUser;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import frame.kit.HttpKit;
import frame.kit.StringKit;

/**
 * 登录拦截器
 * 
 * @author hadong
 * 
 */
public class AuthInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		IkeaUser ikeaUser = controller.getSessionAttr("_ikeaUser");
		if (null != ikeaUser) {
			inv.invoke();
		} else {
			controller.setSessionAttr("_nextUrl", HttpKit.getUrl(controller.getRequest()));
			controller.renderError(401);
		}
	}

	// 免登录actionKey
	@SuppressWarnings("serial")
	public static List<String> noAuthActKey = new ArrayList<String>() {
		{
			add("/login");
			add("/logout");
		}
	};

	public boolean CanVisit(String actionKey) {
		if (StringKit.isNotBlank(actionKey) && noAuthActKey.contains(actionKey)) {
			return true;
		}
		return false;
	}
}