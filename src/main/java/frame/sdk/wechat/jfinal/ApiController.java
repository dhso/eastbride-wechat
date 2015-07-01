package frame.sdk.wechat.jfinal;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import frame.sdk.wechat.api.ApiConfig;

/**
 * 所有使用 Api 的 controller 需要继承此类
 */
@Before(ApiInterceptor.class)
public abstract class ApiController extends Controller {
	public abstract ApiConfig getApiConfig();
}
