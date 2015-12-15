/**
 * Copyright (c) 2011-2015, James Zhan 瑭规尝 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package frame.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.render.Render;

import frame.kit.StringKit;

public class SwitchViewInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		String template = "";
		if (isMobile(c.getRequest())) {
			template = "mobile";
		} else {
			template = "pc";
		}
		c.setSessionAttr("_template", template);
		inv.invoke();
		switchView(template, c);
	}

	public void switchView(String template, Controller c) {
		Render render = c.getRender();
		if (render != null) {
			String view = render.getView();
			if (view != null) {
				if (view.startsWith("/"))
					view = "/" + template + view;
				else
					view = template + "/" + view;

				render.setView(view);
			}
		}
	}

	/**
	 * 判断是否是手机
	 * 
	 * @param request
	 * @return
	 */
	public Boolean isMobile(HttpServletRequest request) {
		if (StringKit.containStr(request.getHeader("user-agent"), "Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod")) {
			return true;
		}
		return false;
	}

}