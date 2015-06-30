package frame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ReqResInViewInterceptor implements Interceptor {
	public void intercept(Invocation inv) {
		inv.invoke();
		HttpServletRequest hreq = inv.getController().getRequest();
		HttpServletResponse hres = inv.getController().getResponse();
		inv.getController().setAttr("request", hreq);
		inv.getController().setAttr("response", hres);
	}
}
