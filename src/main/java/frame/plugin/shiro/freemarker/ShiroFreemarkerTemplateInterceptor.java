package frame.plugin.shiro.freemarker;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class ShiroFreemarkerTemplateInterceptor implements Interceptor {
	@Override
	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		c.setAttr("hasPermission", new ShiroFreemarkerKit());
		inv.invoke();
	}
}
