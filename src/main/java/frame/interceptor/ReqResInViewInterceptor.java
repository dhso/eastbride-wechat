package frame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class ReqResInViewInterceptor implements Interceptor {
	public void intercept(ActionInvocation ai) {
		ai.invoke();
		HttpServletRequest hreq = ai.getController().getRequest();
		HttpServletResponse hres = ai.getController().getResponse();
		ai.getController().setAttr("request", hreq);
		ai.getController().setAttr("response", hres);
	}
}
