package frame.sdk.tuling;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfinal.kit.PropKit;

import frame.kit.HttpKit;

public class TuLingSdk {

	/**
	 * 图灵机器人
	 * 
	 * @param ask
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static String askTuling(String ask,String uid) throws JsonProcessingException,
			IOException {
		String url = "http://www.tuling123.com/openapi/api"
				+"?key=".concat(PropKit.get("tuling.appkey"))
				+ "&info=".concat(URLEncoder.encode(ask, "UTF-8"))
				+ "&userid=".concat(uid);
		Map<String, String> headers = new HashMap<String, String>();
		String result = HttpKit.get(url, null, headers);
		return result;
	}

}
