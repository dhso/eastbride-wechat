package config;

import modules.system.controller.SystemController;
import modules.wechat.controller.WechatApiController;
import modules.wechat.controller.WechatController;
import modules.wechat.controller.WechatMsgController;

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
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.RedirectRender;
import com.jfinal.render.Render;

import frame.interceptor.ReqResInViewInterceptor;
import frame.kit.StringKit;
import frame.plugin.event.EventPlugin;
import frame.plugin.freemarker.BlockDirective;
import frame.plugin.freemarker.ExtendsDirective;
import frame.plugin.freemarker.OverrideDirective;
import frame.plugin.tablebind.AutoTableBindPlugin;
import frame.plugin.tablebind.SimpleNameStyles;
import frame.sdk.fetion.kit.FetionPlugin;
import frame.sdk.wechat.api.ApiConfigKit;

public class BaseConfig extends JFinalConfig {

	private Routes routes;

	public void configConstant(Constants me) {
		// 加载配置/国际化
		PropKit.use("config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		// 微信设置
		ApiConfigKit.setDevMode(me.getDevMode());
		// 设置错误模板
		me.setErrorView(401, "/security/signin");
		me.setErrorRenderFactory(new IErrorRenderFactory() {
			@Override
			public Render getRender(int errorCode, String view) {
				if (StringKit.isBlank(view)) {
					return new RedirectRender("/system/error/".concat(String.valueOf(errorCode)), false);
				}
				return new RedirectRender(view, false);
			}
		});
	}

	public void configRoute(Routes me) {
		this.routes = me;
		me.add("/system", SystemController.class, "/system");// 系统
		me.add("/wechat", WechatController.class, "/weixin");// 微信
		me.add("/wechatMsg", WechatMsgController.class);// 微信消息
		me.add("/wechatApi", WechatApiController.class, "/wechatApi");// 微信接口
	}

	public void configPlugin(Plugins me) {
		// 添加fetion支持
		me.add(new FetionPlugin(PropKit.getLong("fetion.mobile"), PropKit.get("fetion.password")));
		// 添加缓存支持
		me.add(new EhCachePlugin(BaseConfig.class.getClassLoader().getResource("ehcache-model.xml")));
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("jdbcUser"), PropKit.get("jdbcPassword"), PropKit.get("jdbcDriver"));
		WallFilter wallFilter = new WallFilter();
		wallFilter.setDbType(JdbcConstants.MYSQL);
		druidPlugin.addFilter(wallFilter);
		druidPlugin.addFilter(new StatFilter());
		me.add(druidPlugin);
		// 添加自动绑定model与表插件
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin, SimpleNameStyles.LOWER_UNDERLINE);
		autoTableBindPlugin.setShowSql(true);
		// autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
		autoTableBindPlugin.setDevMode(PropKit.getBoolean("devMode", false));
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
		FreeMarkerRender.getConfiguration().setClassicCompatible(true);
		FreeMarkerRender.getConfiguration().setSharedVariable("block", new BlockDirective());
		FreeMarkerRender.getConfiguration().setSharedVariable("override", new OverrideDirective());
		FreeMarkerRender.getConfiguration().setSharedVariable("extends", new ExtendsDirective());
	}
}