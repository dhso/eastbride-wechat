package config;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();

	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}

	public static Prop cfgPro = PropKit.use("config.txt");

	public static Prop msgPro = PropKit.use("config.txt");
}
