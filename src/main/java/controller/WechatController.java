package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import model.Config;
import model.Customer;
import model.CustomerWifi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.ext.plugin.config.ConfigKit;

import frame.kit.DateKit;
import frame.kit.HttpKit;
import frame.kit.StringKit;
import frame.sdk.fetion.FetionException;
import frame.sdk.fetion.Result;
import frame.sdk.fetion.kit.FetionKit;
import frame.sdk.simsimi.SimsimiSdk;
import frame.sdk.wechat.msg.in.InImageMsg;
import frame.sdk.wechat.msg.in.InLinkMsg;
import frame.sdk.wechat.msg.in.InLocationMsg;
import frame.sdk.wechat.msg.in.InTextMsg;
import frame.sdk.wechat.msg.in.InVideoMsg;
import frame.sdk.wechat.msg.in.InVoiceMsg;
import frame.sdk.wechat.msg.in.event.InFollowEvent;
import frame.sdk.wechat.msg.in.event.InLocationEvent;
import frame.sdk.wechat.msg.in.event.InMenuEvent;
import frame.sdk.wechat.msg.in.event.InQrCodeEvent;
import frame.sdk.wechat.msg.in.speech_recognition.InSpeechRecognitionResults;
import frame.sdk.wechat.msg.out.OutImageMsg;
import frame.sdk.wechat.msg.out.OutMusicMsg;
import frame.sdk.wechat.msg.out.OutNewsMsg;
import frame.sdk.wechat.msg.out.OutTextMsg;
import frame.sdk.wechat.msg.out.OutVoiceMsg;
import frame.sdk.wechat.weixin.WeixinController;

/**
 * 将此 UiController 在YourJFinalConfig 中注册路由， 并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该 DemoController 继承自父类 WeixinController 的 index 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class WechatController extends WeixinController {
	/**
	 * 实现父类抽方法，处理文本消息 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal weixin sdk的基本功能： 本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg， 其它类型的消息会在随后的方法中进行测试
	 */
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String msgContent = inTextMsg.getContent().trim();
		if (StringKit.hasObject(msgContent, "help", "HELP", "帮助")) {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent(ConfigKit.getStr("msg.helpStr"));
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

	/**
	 * 实现父类抽方法，处理图片消息
	 */
	protected void processInImageMsg(InImageMsg inImageMsg) {
		OutImageMsg outMsg = new OutImageMsg(inImageMsg);
		// 将刚发过来的图片再发回去
		outMsg.setMediaId(inImageMsg.getMediaId());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理语音消息
	 */
	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
		OutVoiceMsg outMsg = new OutVoiceMsg(inVoiceMsg);
		// 将刚发过来的语音再发回去
		outMsg.setMediaId(inVoiceMsg.getMediaId());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理视频消息
	 */
	protected void processInVideoMsg(InVideoMsg inVideoMsg) {
		/*
		 * 腾讯 api 有 bug，无法回复视频消息，暂时回复文本消息代码测试 OutVideoMsg outMsg = new OutVideoMsg(inVideoMsg); outMsg.setTitle("OutVideoMsg 发送"); outMsg.setDescription("刚刚发来的视频再发回去"); // 将刚发过来的视频再发回去，经测试证明是腾讯官方的 api 有 bug，待 api bug 却除后再试 outMsg.setMediaId(inVideoMsg.getMediaId()); render(outMsg);
		 */
		OutTextMsg outMsg = new OutTextMsg(inVideoMsg);
		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inVideoMsg.getMediaId());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理地址位置消息
	 */
	protected void processInLocationMsg(InLocationMsg inLocationMsg) {
		OutTextMsg outMsg = new OutTextMsg(inLocationMsg);
		outMsg.setContent("已收到地理位置消息:" + "\nlocation_X = " + inLocationMsg.getLocation_X() + "\nlocation_Y = " + inLocationMsg.getLocation_Y() + "\nscale = " + inLocationMsg.getScale() + "\nlabel = " + inLocationMsg.getLabel());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理链接消息 特别注意：测试时需要发送我的收藏中的曾经收藏过的图文消息，直接发送链接地址会当做文本消息来发送
	 */
	protected void processInLinkMsg(InLinkMsg inLinkMsg) {
		OutNewsMsg outMsg = new OutNewsMsg(inLinkMsg);
		outMsg.addNews("链接消息已成功接收", "链接使用图文消息的方式发回给你，还可以使用文本方式发回。点击图文消息可跳转到链接地址页面，是不是很好玩 :)", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", inLinkMsg.getUrl());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理关注/取消关注消息
	 */
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
		String customerOpenid = inFollowEvent.getFromUserName();
		String msgEvent = inFollowEvent.getEvent();
		if ("subscribe".equalsIgnoreCase(msgEvent)) {
			// 关注事件
			Customer.dao.subscribe(customerOpenid, "直接关注");
			OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
			outMsg.setContent(Config.dao.getCfgKey("wx.welcome"));
			render(outMsg);
		} else if ("unsubscribe".equalsIgnoreCase(msgEvent)) {
			// 取消关注事件，将无法接收到传回的信息
			Customer.dao.unsubscribe(customerOpenid);
			renderNull();
		}

	}

	/**
	 * 实现父类抽方法，处理扫描带参数二维码事件
	 */
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
		OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
		outMsg.setContent("processInQrCodeEvent() 方法测试成功");
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理上报地理位置事件
	 */
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
		outMsg.setContent("processInLocationEvent() 方法测试成功");
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理自定义菜单事件
	 */
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		String customerOpenid = inMenuEvent.getFromUserName();
		String msgEvent = inMenuEvent.getEvent().trim();
		String msgEventKey = inMenuEvent.getEventKey().trim();

		if ("click".equalsIgnoreCase(msgEvent)) {
			if ("CK_SHOP".equalsIgnoreCase(msgEventKey)) {
				OutNewsMsg outMsg = new OutNewsMsg(inMenuEvent);
				outMsg.addNews("标题", "描述", "http://wcdn.u.qiniudn.com/pic/shopping.jpg", "http://url.com?openid=" + inMenuEvent.getFromUserName());
				render(outMsg);
			} else if ("c_access_wifi".equalsIgnoreCase(msgEventKey)) {
				CustomerWifi customerWifi = CustomerWifi.dao.applyForWifiCaptcha(customerOpenid);
				OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
				outMsg.setContent(MessageFormat.format(ConfigKit.getStr("msg.zh.wifiCaptcha"), customerWifi.getStr("captcha"), DateKit.formatDateTime(customerWifi.getDate("expired_dt"))));
				render(outMsg);
			} else if ("c_send_sms".equalsIgnoreCase(msgEventKey)) {
				Result result = null;
				CustomerWifi customerWifi = CustomerWifi.dao.applyForWifiCaptcha(customerOpenid);
				result = FetionKit.sendSMS(15262731827L, MessageFormat.format(ConfigKit.getStr("msg.zh.wifiCaptcha"), customerWifi.getStr("captcha"), DateKit.formatDateTime(customerWifi.getDate("expired_dt"))));
				renderJson(result);
			}
		}
	}

	/**
	 * 实现父类抽方法，处理接收语音识别结果
	 */
	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
		renderOutTextMsg("语音识别结果： " + inSpeechRecognitionResults.getRecognition());
	}
}
