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

	public static void init(Long fromMobile, String password) throws FetionException {
		FetionKit.fromMobile = fromMobile;
		FetionKit.password = password;
		exec();
	}

	public static void exec() throws FetionException {
		FetionKit.fetion = new Fetion(fromMobile);
		FetionKit.fetionConsole = fetion.login(password);
	}

	public static Fetion Console() {
		return fetion;
	}

	public static FetionConsole getFetionConsole() {
		return fetionConsole;
	}

	public static Result sendSMS(Long toMobile, String message) throws FetionException {
		Result result = null;
		Buddy buddy = fetionConsole.getUserInfo().getContact().findBuddy(toMobile);// 根据飞信号码查询好友
		result = fetionConsole.sendSMSMessage(buddy, message);// 发送手机短信
		return result;
	}
}
