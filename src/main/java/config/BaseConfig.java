package config;

import modules.crm.controller.CrmController;
import modules.system.controller.SecurityController;
import modules.wechat.controller.WechatApiController;
import modules.wechat.controller.WechatMsgController;
import modules.weshop.controller.ShopController;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
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
import com.jfinal.i18n.I18nInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.RedirectRender;
import com.jfinal.render.Render;

import frame.interceptor.ReqResInViewInterceptor;
import frame.plugin.event.EventPlugin;
import frame.plugin.shiro.core.ShiroInterceptor;
import frame.plugin.shiro.core.ShiroPlugin;
import frame.plugin.shiro.freemarker.ShiroTags;
import frame.plugin.tablebind.AutoTableBindPlugin;
import frame.plugin.tablebind.SimpleNameStyles;
import frame.sdk.fetion.kit.FetionPlugin;
import frame.sdk.wechat.api.ApiConfigKit;

public class BaseConfig extends JFinalConfig {

	private Routes routes;

	public void configConstant(Constants me) {
		// 加载配置/国际化
		PropKit.use("config.txt");
		me.setI18nDefaultBaseName("i18n");
		me.setI18nDefaultLocale("zh_CN");
		me.setDevMode(PropKit.getBoolean("wx.devMode", false));
		// 微信设置
		ApiConfigKit.setDevMode(me.getDevMode());
		// 设置错误模板
		me.setErrorView(401, "/security/signin");
		me.setErrorView(403, "/security/signin");
		me.setErrorView(404, "/security/err404");
		me.setErrorView(500, "/security/err500");
		me.setErrorRenderFactory(new IErrorRenderFactory() {
			@Override
			public Render getRender(int errorCode, String view) {
				return new RedirectRender(view, true);
			}
		});
	}

	public void configRoute(Routes me) {
		this.routes = me;
		me.add("/security", SecurityController.class, "/security");// 安全
		me.add("/shop", ShopController.class, "/shop");// 商城
		me.add("/wechat", WechatMsgController.class);// 微信
		me.add("/wechatApi", WechatApiController.class, "/wechatApi");// 微信API
		me.add("/crm", CrmController.class, "/crm");// CRM
	}

	public void configPlugin(Plugins me) {
		// 添加fetion支持
		me.add(new FetionPlugin(PropKit.getLong("fetion.mobile"), PropKit.get("fetion.password")));
		// 添加shiro支持
		me.add(new ShiroPlugin(routes));
		// 添加缓存支持
		me.add(new EhCachePlugin(BaseConfig.class.getClassLoader().getResource("ehcache-model.xml")));
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("wx.jdbcUrl"), PropKit.get("wx.jdbcUser"), PropKit.get("wx.jdbcPassword"), PropKit.get("wx.jdbcDriver"));
		WallFilter wallFilter = new WallFilter();
		wallFilter.setDbType(JdbcConstants.MYSQL);
		druidPlugin.addFilter(wallFilter);
		druidPlugin.addFilter(new StatFilter());
		me.add(druidPlugin);
		// 添加自动绑定model与表插件
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin, SimpleNameStyles.LOWER_UNDERLINE);
		autoTableBindPlugin.setShowSql(true);
		autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
		autoTableBindPlugin.setDevMode(PropKit.getBoolean("wx.devMode", false));
		me.add(autoTableBindPlugin);
		// 添加消息驱动插件
		EventPlugin evevtPlugin = new EventPlugin();
		// 设置为异步，默认同步
		evevtPlugin.async();
		// 设置扫描jar包，默认不扫描
		evevtPlugin.scanJar();
		// 设置监听器默认包，默认全扫描
		evevtPlugin.scanPackage("modules");
		me.add(evevtPlugin);
	}

	public void configInterceptor(Interceptors me) {
		// shiro拦截器
		me.add(new ShiroInterceptor());
		// shiroFreemarker拦截器
		// me.add(new ShiroFreemarkerTemplateInterceptor());
		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor());
		// 让 模版 可以使用request/response
		me.add(new ReqResInViewInterceptor());
		// 让 模版 可以使用I18n
		me.add(new I18nInterceptor());
	}

	public void configHandler(Handlers me) {
		me.add(new UrlSkipHandler(".*/static/.*", false));
		me.add(new ContextPathHandler("baseUrl"));
		me.add(new DruidStatViewHandler("/druid"));
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}

	public void afterJFinalStart() {
		super.afterJFinalStart();
		FreeMarkerRender.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}
}