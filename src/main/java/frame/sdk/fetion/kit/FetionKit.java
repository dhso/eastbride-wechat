package frame.sdk.fetion.kit;

import frame.sdk.fetion.Fetion;
import frame.sdk.fetion.FetionConsole;
import frame.sdk.fetion.FetionException;
import frame.sdk.fetion.Result;
import frame.sdk.fetion.user.Buddy;

public class FetionKit {
	public static Fetion fetion;
	public static FetionConsole fetionConsole;
	public static Long fromMobile;
	public static String password;

	public static void init(Long fromMobile, String password) {
		FetionKit.fromMobile = fromMobile;
		FetionKit.password = password;
	}

	// 登录
	public static void login() {
		FetionKit.fetion = new Fetion(fromMobile);
		try {
			FetionKit.fetionConsole = fetion.login(password);
		} catch (FetionException e) {
			e.printStackTrace();
		}
	}

	// 关闭
	public static void close() {
		if (fetionConsole != null) {
			try {
				fetionConsole.close();
			} catch (FetionException e) {
				e.printStackTrace();
			}
		}
	}

	public static Fetion getFetion() {
		return fetion;
	}

	public static FetionConsole getFetionConsole() {
		return fetionConsole;
	}

	// 发送手机短信
	public static Result sendSMS(Long toMobile, String message) {
		Result result = null;
		try {
			login();
			Buddy buddy = fetionConsole.getUserInfo().getContact().findBuddy(toMobile);// 根据飞信号码查询好友
			if (null != buddy) {
				result = fetionConsole.sendSMSMessage(buddy, message);
			}
		} catch (FetionException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
