package frame.sdk.wechat.weixin;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * NotAction
 */
public class NotAction implements Interceptor {
	public void intercept(ActionInvocation ai) {
		ai.getController().renderError(404);
	}
}