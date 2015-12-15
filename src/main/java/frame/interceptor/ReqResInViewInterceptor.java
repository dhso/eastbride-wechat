package frame.interceptor;

import modules.ikea.entity.IkeaUser;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Logger;

import frame.kit.HttpKit;

public class ReqResInViewInterceptor implements Interceptor {
	private static final Logger logger = Logger.getLogger(ReqResInViewInterceptor.class);

	public void intercept(Invocation inv) {
		inv.invoke();
		IkeaUser ikeaUser = inv.getController().getSessionAttr("_ikeaUser");
		logger.info(" membershipnum:" + (null != ikeaUser ? ikeaUser.getMmbMembershipnum() : "") + "|~~|device:" + inv.getController().getSessionAttr("_template") + "|~~|source:" + inv.getController().getSessionAttr("_source") + "|~~|visit:" + HttpKit.getUrl(inv.getController().getRequest()));
		// TokenManager.createToken(inv.getController(), "jockillerToken", 30 * 60);
		inv.getController().setAttr("baiduTongjiId", PropKit.get("baidu.tongji.id", ""));
	}
}
