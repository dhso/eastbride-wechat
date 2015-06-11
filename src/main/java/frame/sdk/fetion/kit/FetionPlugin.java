package frame.sdk.fetion.kit;

import com.jfinal.plugin.IPlugin;

public class FetionPlugin implements IPlugin {

	private Long fromMobile;
	private String password;

	public FetionPlugin() {

	}

	public FetionPlugin(Long fromMobile, String password) {
		this.fromMobile = fromMobile;
		this.password = password;
	}

	@Override
	public boolean start() {
		FetionKit.init(fromMobile, password);
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}

}
