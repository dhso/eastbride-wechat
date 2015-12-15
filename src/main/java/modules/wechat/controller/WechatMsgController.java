package modules.wechat.controller;

import java.io.IOException;
import java.util.Iterator;

import modules.wechat.model.WechatConfigModel;
import modules.wechat.model.WechatCustomerModel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;

import frame.kit.StringKit;
import frame.sdk.tuling.TuLingSdk;
import frame.sdk.wechat.api.ApiConfig;
import frame.sdk.wechat.jfinal.MsgController;
import frame.sdk.wechat.msg.in.InImageMsg;
import frame.sdk.wechat.msg.in.InLinkMsg;
import frame.sdk.wechat.msg.in.InLocationMsg;
import frame.sdk.wechat.msg.in.InShortVideoMsg;
import frame.sdk.wechat.msg.in.InTextMsg;
import frame.sdk.wechat.msg.in.InVideoMsg;
import frame.sdk.wechat.msg.in.InVoiceMsg;
import frame.sdk.wechat.msg.in.event.InCustomEvent;
import frame.sdk.wechat.msg.in.event.InFollowEvent;
import frame.sdk.wechat.msg.in.event.InLocationEvent;
import frame.sdk.wechat.msg.in.event.InMassEvent;
import frame.sdk.wechat.msg.in.event.InMenuEvent;
import frame.sdk.wechat.msg.in.event.InQrCodeEvent;
import frame.sdk.wechat.msg.in.event.InTemplateMsgEvent;
import frame.sdk.wechat.msg.in.speech_recognition.InSpeechRecognitionResults;
import frame.sdk.wechat.msg.out.OutCustomMsg;
import frame.sdk.wechat.msg.out.OutNewsMsg;
import frame.sdk.wechat.msg.out.OutTextMsg;

//将此 Controller 在YourJFinalConfig 中注册路由，
//并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该
//DemoController 继承自父类 WeixinController 的 index
//方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
public class WechatMsgController extends MsgController {
	// 如果要支持多公众账号，只需要在此返回各个公众号对应的 ApiConfig 对象即可
	// 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	// 配置微信 API 相关常量
	// 1：true进行加密且必须配置 encodingAesKey
	// 2：false采用明文模式，同时也支持混合模式
	public ApiConfig getApiConfig() {
		return new ApiConfig(PropKit.get("token"), PropKit.get("appId"), PropKit.get("appSecret"), PropKit.getBoolean("messageEncrypt"), PropKit.get("encodingAesKey"));
	}

	// 处理文本消息
	@Override
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String msgContent = inTextMsg.getContent().trim();
		if (StringKit.hasObject(msgContent, "help", "HELP", "帮助")) {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent(WechatConfigModel.dao.getConfigVal("help"));
			render(outMsg);
		} else if (StringKit.containStr(msgContent, "优惠", "套餐", "价格")) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews("标题", "测试信息", "http://wcdn.u.qiniudn.com/img/weatherreport.jpg", "");
			render(outMsg);
		} else {
			try {
				String result = TuLingSdk.askTuling(inTextMsg.getContent(), inTextMsg.getFromUserName());
				if (StringKit.isNotBlank(result)) {
					JSONObject jsonResult = JSON.parseObject(result);
					Integer code = jsonResult.getInteger("code");
					switch (code) {
					case 100000:
						OutTextMsg textMsg = new OutTextMsg(inTextMsg);
						textMsg.setContent(jsonResult.getString("text"));
						render(textMsg);
						break;
					case 200000:
						OutNewsMsg linkMsg = new OutNewsMsg(inTextMsg);
						linkMsg.addNews(jsonResult.getString("text"), "点击查看", "http://78re1z.com1.z0.glb.clouddn.com/img/search.jpg", jsonResult.getString("url"));
						render(linkMsg);
						break;
					case 302000:
						OutNewsMsg newsMsg = new OutNewsMsg(inTextMsg);
						JSONArray newsArrayResult = jsonResult.getJSONArray("list");
						Iterator<Object> newsIterator = newsArrayResult.iterator();
						Integer newsCount = 0;
						while (newsIterator.hasNext() && newsCount < 10) {
							JSONObject object = (JSONObject) newsIterator.next();
							newsMsg.addNews(object.getString("article"), object.getString("source"), object.getString("icon"), object.getString("detailurl"));
							newsCount++;
						}
						render(newsMsg);
						break;
					case 305000:
						OutNewsMsg trainMsg = new OutNewsMsg(inTextMsg);
						JSONArray trainArrayResult = jsonResult.getJSONArray("list");
						Iterator<Object> trainIterator = trainArrayResult.iterator();
						Integer trainCount = 0;
						while (trainIterator.hasNext() && trainCount < 10) {
							JSONObject object = (JSONObject) trainIterator.next();
							trainMsg.addNews(object.getString("trainnum"), object.getString("start").concat("(").concat(object.getString("starttime")).concat(")").concat(" - ").concat(object.getString("terminal")).concat("(").concat(object.getString("endtime")).concat(")"),
									object.getString("icon"), object.getString("detailurl"));
							trainCount++;
						}
						render(trainMsg);
						break;
					case 308000:
						OutNewsMsg cookMsg = new OutNewsMsg(inTextMsg);
						JSONArray cookArrayResult = jsonResult.getJSONArray("list");
						Iterator<Object> cookIterator = cookArrayResult.iterator();
						Integer cookCount = 0;
						while (cookIterator.hasNext() && cookCount < 10) {
							JSONObject object = (JSONObject) cookIterator.next();
							cookMsg.addNews(object.getString("name"), object.getString("info"), object.getString("icon"), object.getString("detailurl"));
							cookCount++;
						}
						render(cookMsg);
						break;
					default:
						OutTextMsg defaultTextMsg = new OutTextMsg(inTextMsg);
						defaultTextMsg.setContent("听不懂您说什么哟！");
						render(defaultTextMsg);
						break;
					}
				}
			} catch (IOException e) {
				OutTextMsg defaultTextMsg = new OutTextMsg(inTextMsg);
				defaultTextMsg.setContent("机器人没电咯，暂时不能回答您的问题！");
				render(defaultTextMsg);
			}
		}

	}

	// 处理图片消息
	@Override
	protected void processInImageMsg(InImageMsg inImageMsg) {
		// 转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inImageMsg);
		render(outCustomMsg);

	}

	// 处理语音消息
	@Override
	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
		// TODO Auto-generated method stub

	}

	// 处理视频消息
	@Override
	protected void processInVideoMsg(InVideoMsg inVideoMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg) {
		OutTextMsg outMsg = new OutTextMsg(inShortVideoMsg);
		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inShortVideoMsg.getMediaId());
		render(outMsg);
	}

	// 处理地址位置消息
	@Override
	protected void processInLocationMsg(InLocationMsg inLocationMsg) {
		// TODO Auto-generated method stub

	}

	// 处理链接消息
	// 特别注意：测试时需要发送我的收藏中的曾经收藏过的图文消息，直接发送链接地址会当做文本消息来发送
	@Override
	protected void processInLinkMsg(InLinkMsg inLinkMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInCustomEvent(InCustomEvent inCustomEvent) {
		System.out.println("processInCustomEvent() 方法测试成功");
	}

	// 处理关注/取消关注消息
	@Override
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
		if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(inFollowEvent.getEvent())) {
			// 关注事件
			WechatCustomerModel.dao.subscribe(inFollowEvent.getFromUserName(), "直接关注");
			OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
			outMsg.setContent(WechatConfigModel.dao.getConfigVal("welcome"));
			render(outMsg);
		}
		if (InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(inFollowEvent.getEvent())) {
			// 取消关注事件，将无法接收到传回的信息
			WechatCustomerModel.dao.unsubscribe(inFollowEvent.getFromUserName());
		}

	}

	// 处理扫描带参数二维码事件
	@Override
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
		if (InQrCodeEvent.EVENT_INQRCODE_SUBSCRIBE.equals(inQrCodeEvent.getEvent())) {
			WechatCustomerModel.dao.subscribe(inQrCodeEvent.getFromUserName(), "扫码关注");
			OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
			outMsg.setContent(WechatConfigModel.dao.getConfigVal("welcome"));
			render(outMsg);
		}
		if (InQrCodeEvent.EVENT_INQRCODE_SCAN.equals(inQrCodeEvent.getEvent())) {
			WechatCustomerModel.dao.unsubscribe(inQrCodeEvent.getFromUserName());
		}

	}

	// 处理上报地理位置事件
	@Override
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processInMassEvent(InMassEvent inMassEvent) {
		System.out.println("processInMassEvent() 方法测试成功");
	}

	// 处理自定义菜单事件
	@Override
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		String customerOpenid = inMenuEvent.getFromUserName();
		String msgEvent = inMenuEvent.getEvent().trim();
		String msgEventKey = inMenuEvent.getEventKey().trim();

		if ("click".equalsIgnoreCase(msgEvent)) {
			if ("CK_SHOP".equalsIgnoreCase(msgEventKey)) {
				OutNewsMsg outMsg = new OutNewsMsg(inMenuEvent);
				outMsg.addNews("标题", "描述", "http://wcdn.u.qiniudn.com/pic/shopping.jpg", "http://url.com?openid=" + inMenuEvent.getFromUserName());
				render(outMsg);
			}
		}

	}

	// 处理接收语音识别结果
	@Override
	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
		// TODO Auto-generated method stub

	}

	// 处理接收到的模板消息是否送达成功通知事件
	@Override
	protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
		// TODO Auto-generated method stub

	}
}
