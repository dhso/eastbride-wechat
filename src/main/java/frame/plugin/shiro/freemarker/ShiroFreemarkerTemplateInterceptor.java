package frame.plugin.shiro.freemarker;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ShiroFreemarkerTemplateInterceptor implements Interceptor {
	@Override
	public void intercept(ActionInvocation ai) {
		Controller c = ai.getController();
		c.setAttr("hasPermission", new ShiroFreemarkerKit());
		ai.invoke();
	}
}
