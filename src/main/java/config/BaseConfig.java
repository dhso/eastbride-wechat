package config;

import modules.system.controller.SecurityController;
import modules.wechat.controller.WechatApiController;
import modules.wechat.controller.WechatMsgController;
import modules.weshop.controller.ShopController;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jagregory.shiro.freemarker.ShiroTags;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.RedirectRender;
import com.jfinal.render.Render;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

import frame.interceptor.ReqResInViewInterceptor;
import frame.sdk.fetion.kit.FetionPlugin;

public class BaseConfig extends JFinalConfig {

	private Routes routes;

	@Override
	public void configConstant(Constants me) {

		me.setDevMode(Global.cfgPro.getBoolean("wx.devMode", false));
		me.setError401View("/security/signin");
		me.setError403View("/security/signin");
		me.setError404View("/security/err404");
		me.setError500View("/security/err500");
		me.setErrorRenderFactory(new IErrorRenderFactory() {
			@Override
			public Render getRender(int errorCode, String view) {
				return new RedirectRender(view);
			}
		});
		// 微信设置
		ApiConfigKit.setDevMode(me.getDevMode());
	}

	@Override
	public void configRoute(Routes me) {
		this.routes = me;
		me.add("/security", SecurityController.class, "/security");// 安全
		me.add("/shop", ShopController.class, "/shop");// 商城
		me.add("/wechat", WechatMsgController.class);// 微信
		me.add("/wechatApi", WechatApiController.class, "/wechatApi");
	}

	@Override
	public void configPlugin(Plugins me) {
		// 添加fetion支持
		me.add(new FetionPlugin(Global.cfgPro.getLong("wx.fetion.mobile"), Global.cfgPro.get("wx.fetion.password")));
		// 添加shiro支持
		// me.add(new ShiroPlugin(routes));
		// 添加缓存支持
		me.add(new EhCachePlugin(BaseConfig.class.getClassLoader().getResource("ehcache-model.xml")));
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(Global.cfgPro.get("wx.jdbcUrl"), Global.cfgPro.get("wx.jdbcUser"), Global.cfgPro.get("wx.jdbcPassword"), Global.cfgPro.get("wx.jdbcDriver"));
		WallFilter wallFilter = new WallFilter();
		wallFilter.setDbType(JdbcConstants.MYSQL);
		druidPlugin.addFilter(wallFilter);
		druidPlugin.addFilter(new StatFilter());
		me.add(druidPlugin);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// shiro拦截器
		// me.add(new ShiroInterceptor());
		// shiroFreemarker拦截器
		// me.add(new ShiroFreemarkerTemplateInterceptor());
		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor());
		// 让 模版 可以使用request/response
		me.add(new ReqResInViewInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new UrlSkipHandler(".*/static/.*", false));
		me.add(new ContextPathHandler("baseUrl"));
		me.add(new DruidStatViewHandler("/druid"));
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}

	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
		FreeMarkerRender.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}
}