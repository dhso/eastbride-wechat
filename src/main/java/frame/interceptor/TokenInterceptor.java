package frame.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.token.TokenManager;

/**
 * 防止表单重复提交
 * 
 * @author hadong
 * 
 *         在跳转到view之前,生成token
 * 
 *         TokenManager.createToken("jockillerToken", 30*60);
 * 
 *         在页面中加入隐藏域
 * 
 *         <input type="hidden" name="jockillerToken" value="${jockillerToken}"/>
 * 
 *         在form条的action方法上面加上
 * 
 *         Before(value = {TokenInterceptor.class })
 */

public class TokenInterceptor implements Interceptor {
	public void intercept(Invocation inv) {
		boolean token = TokenManager.validateToken(inv.getController(), "jockillerToken");
		if (!token) {
			inv.getController().renderError(405);
			return;
		}
		inv.invoke();
	}
}
