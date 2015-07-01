package modules.wechat.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import modules.system.entity.Config;
import modules.wechat.entity.Customer;
import modules.wechat.model.ShopWifi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.i18n.I18n;
import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgController;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InTemplateMsgEvent;
import com.jfinal.weixin.sdk.msg.out.OutMusicMsg;
import com.jfinal.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

import frame.kit.DateKit;
import frame.kit.StringKit;
import frame.sdk.fetion.Result;
import frame.sdk.fetion.kit.FetionKit;
import frame.sdk.simsimi.SimsimiSdk;

/**
 * 将此 UiController 在YourJFinalConfig 中注册路由， 并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该 DemoController 继承自父类 WeixinController 的 index 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class WechatMsgController extends MsgController {

	@Override
	public ApiConfig getApiConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String msgContent = inTextMsg.getContent().trim();
		if (StringKit.hasObject(msgContent, "help", "HELP", "帮助")) {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent(I18n.use().get("msg.helpStr"));
			render(outMsg);
		} else if (StringKit.containStr(msgContent, "包子", "早点", "早饭")) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews("标题", "描述", "http://wcdn.u.qiniudn.com/pic/shopping.jpg", "http://url.com?openid=" + inTextMsg.getFromUserName());
			render(outMsg);
		} else if (StringKit.containStr(msgContent, "天气预报", "天气", "温度", "下雨")) {
			try {
				String uri = "http://apix.sinaapp.com/weather/?appkey=trialuser&city=" + URLEncoder.encode(StringKit.replaceStrs(msgContent, "", "天气预报", "天气", "温度", "下雨"), "UTF-8");
				JSONArray weather = JSON.parseArray(HttpKit.get(uri));
				OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
				for (int i = 0; i < weather.size(); i++) {
					if (i == 0) {
						outMsg.addNews(weather.getJSONObject(i).getString("Title"), weather.getJSONObject(i).getString("Description"), "http://wcdn.u.qiniudn.com/img/weatherreport.jpg", "");
					} else {
						outMsg.addNews(weather.getJSONObject(i).getString("Title"), weather.getJSONObject(i).getString("Description"), weather.getJSONObject(i).getString("PicUrl"), "");
					}
				}
				render(outMsg);
			} catch (Exception e) {
				OutTextMsg outMsg = new OutTextMsg(inTextMsg).setContent("找不到地区: " + StringKit.replaceStrs(msgContent, "", "天气", "温度", "下雨", "天气预报"));
				render(outMsg);
			}
		} else if ("music".equalsIgnoreCase(msgContent)) {
			OutMusicMsg outMsg = new OutMusicMsg(inTextMsg);
			outMsg.setTitle("Listen To Your Heart");
			outMsg.setDescription("建议在 WIFI 环境下流畅欣赏此音乐");
			outMsg.setMusicUrl("http://www.jfinal.com/Listen_To_Your_Heart.mp3");
			outMsg.setHqMusicUrl("http://www.jfinal.com/Listen_To_Your_Heart.mp3");
			outMsg.setFuncFlag(true);
			render(outMsg);
		} else if ("美女".equalsIgnoreCase(msgContent)) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews("秀色可餐", "JFinal Weixin 极速开发就是这么爽，有木有 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq2GJLC60ECD7rE7n1cvKWRNFvOyib4KGdic3N5APUWf4ia3LLPxJrtyIYRx93aPNkDtib3ADvdaBXmZJg/0",
					"http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200987822&idx=1&sn=7eb2918275fb0fa7b520768854fb7b80#rd");
			render(outMsg);
		} else {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			try {
				outMsg.setContent(SimsimiSdk.askSimsimi(inTextMsg.getContent()));
			} catch (IOException e) {
				outMsg.setContent("小黄鸡被大象打嘴巴了，不能说话了，555~~~");
			}
			render(outMsg);
		}

	}

	@Override
	protected void processInImageMsg(com.jfinal.weixin.sdk.msg.in.InImageMsg inImageMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInVoiceMsg(com.jfinal.weixin.sdk.msg.in.InVoiceMsg inVoiceMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInVideoMsg(com.jfinal.weixin.sdk.msg.in.InVideoMsg inVideoMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInLocationMsg(com.jfinal.weixin.sdk.msg.in.InLocationMsg inLocationMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInLinkMsg(com.jfinal.weixin.sdk.msg.in.InLinkMsg inLinkMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInFollowEvent(com.jfinal.weixin.sdk.msg.in.event.InFollowEvent inFollowEvent) {
		String customerOpenid = inFollowEvent.getFromUserName();
		String msgEvent = inFollowEvent.getEvent();
		if ("subscribe".equalsIgnoreCase(msgEvent)) {
			// 关注事件
			Customer.dao.subscribe(customerOpenid, "直接关注");
			OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
			outMsg.setContent(Config.dao.getCfgValue("wx.welcome"));
			render(outMsg);
		} else if ("unsubscribe".equalsIgnoreCase(msgEvent)) {
			// 取消关注事件，将无法接收到传回的信息
			Customer.dao.unsubscribe(customerOpenid);
			renderNull();
		}

	}

	@Override
	protected void processInQrCodeEvent(com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent inQrCodeEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInLocationEvent(com.jfinal.weixin.sdk.msg.in.event.InLocationEvent inLocationEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInMenuEvent(com.jfinal.weixin.sdk.msg.in.event.InMenuEvent inMenuEvent) {
		String customerOpenid = inMenuEvent.getFromUserName();
		String msgEvent = inMenuEvent.getEvent().trim();
		String msgEventKey = inMenuEvent.getEventKey().trim();

		if ("click".equalsIgnoreCase(msgEvent)) {
			if ("CK_SHOP".equalsIgnoreCase(msgEventKey)) {
				OutNewsMsg outMsg = new OutNewsMsg(inMenuEvent);
				outMsg.addNews("标题", "描述", "http://wcdn.u.qiniudn.com/pic/shopping.jpg", "http://url.com?openid=" + inMenuEvent.getFromUserName());
				render(outMsg);
			} else if ("c_access_wifi".equalsIgnoreCase(msgEventKey)) {
				ShopWifi customerWifi = ShopWifi.dao.applyForWifiCaptcha(customerOpenid);
				OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
				outMsg.setContent(MessageFormat.format(I18n.use().get("msg.zh.wifiCaptcha"), customerWifi.getStr("captcha"), DateKit.formatDateTime(customerWifi.getDate("expired_dt"))));
				render(outMsg);
			} else if ("c_send_sms".equalsIgnoreCase(msgEventKey)) {
				Result result = null;
				ShopWifi customerWifi = ShopWifi.dao.applyForWifiCaptcha(customerOpenid);
				result = FetionKit.sendSMS(15262731827L, I18n.use().format("wifiCaptcha", customerWifi.getStr("captcha"), DateKit.formatDateTime(customerWifi.getDate("expired_dt"))));
				renderJson(result);
			}
		}

	}

	@Override
	protected void processInSpeechRecognitionResults(com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults inSpeechRecognitionResults) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
		// TODO Auto-generated method stub

	}
}
