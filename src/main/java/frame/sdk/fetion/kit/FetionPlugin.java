package frame.sdk.fetion.kit;

import com.jfinal.plugin.IPlugin;

import frame.sdk.fetion.FetionException;

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
		try {
			FetionKit.init(fromMobile, password);
		} catch (FetionException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean stop() {
		if (FetionKit.getFetionConsole() != null) {
			try {
				FetionKit.getFetionConsole().close();
			} catch (FetionException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
