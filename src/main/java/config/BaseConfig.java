package config;

import modules.system.controller.SecurityController;
import modules.wechat.controller.WechatController;
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
import com.jfinal.ext.plugin.config.ConfigPlugin;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.RedirectRender;
import com.jfinal.render.Render;

import frame.interceptor.ReqResInViewInterceptor;
import frame.sdk.fetion.kit.FetionPlugin;
import frame.sdk.wechat.api.ApiConfig;

public class BaseConfig extends JFinalConfig {

	private Routes routes;

	@Override
	public void configConstant(Constants cs) {
		loadPropertyFile("config.txt");
		cs.setDevMode(getPropertyToBoolean("wx.devMode"));
		cs.setError401View("/security/signin");
		cs.setError403View("/security/signin");
		cs.setError404View("/security/err404");
		cs.setError500View("/security/err500");
		cs.setErrorRenderFactory(new IErrorRenderFactory() {
			@Override
			public Render getRender(int errorCode, String view) {
				return new RedirectRender(view);
			}
		});
		// 配置微信 API 相关常量
		ApiConfig.setDevMode(getPropertyToBoolean("wx.devMode"));
		ApiConfig.setUrl(getProperty("wx.url"));
		ApiConfig.setToken(getProperty("wx.token"));
		ApiConfig.setAppId(getProperty("wx.appId"));
		ApiConfig.setAppSecret(getProperty("wx.appSecret"));
	}

	@Override
	public void configRoute(Routes rs) {
		this.routes = rs;
		rs.add("/security", SecurityController.class, "/security");// 安全
		rs.add("/wechat", WechatController.class);// 微信
		rs.add("/shop", ShopController.class, "/shop");// 商城
	}

	@Override
	public void configPlugin(Plugins ps) {
		// 添加fetion支持
		ps.add(new FetionPlugin(getPropertyToLong("wx.fetion.mobile"), getProperty("wx.fetion.password")));
		// 添加shiro支持
		ps.add(new ShiroPlugin(routes));
		// 添加缓存支持
		ps.add(new EhCachePlugin(BaseConfig.class.getClassLoader().getResource("ehcache-model.xml")));
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("wx.jdbcUrl"), getProperty("wx.jdbcUser"), getProperty("wx.jdbcPassword"), getProperty("wx.jdbcDriver"));
		WallFilter wallFilter = new WallFilter();
		wallFilter.setDbType(JdbcConstants.MYSQL);
		druidPlugin.addFilter(wallFilter);
		druidPlugin.addFilter(new StatFilter());
		ps.add(druidPlugin);
		// 添加自动绑定model与表插件
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin, SimpleNameStyles.LOWER_UNDERLINE);
		autoTableBindPlugin.setShowSql(true);
		autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
		// autoTableBindPlugin.setAutoScan(false);
		ps.add(autoTableBindPlugin);
		// 添加资源文件
		ps.add(new ConfigPlugin("config.txt", "message_zh.txt"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// shiro拦截器
		me.add(new ShiroInterceptor());
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