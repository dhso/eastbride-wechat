/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package frame.sdk.wechat.jfinal;

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
import frame.sdk.wechat.msg.in.event.InTemplateMsgEvent;
import frame.sdk.wechat.msg.in.speech_recognition.InSpeechRecognitionResults;

/**
 * MsgControllerAdapter 对 MsgController 部分方法提供了默认实现，
 * 以便开发者不去关注 MsgController 中不需要处理的抽象方法，节省代码量
 */
public abstract class MsgControllerAdapter extends MsgController {
	
	protected abstract void processInFollowEvent(InFollowEvent inFollowEvent);
	
	protected abstract void processInTextMsg(InTextMsg inTextMsg);
	
	protected abstract void processInMenuEvent(InMenuEvent inMenuEvent);
	
	protected void processInImageMsg(InImageMsg inImageMsg) {
		
	}
	
	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
		
	}
	
	protected void processInVideoMsg(InVideoMsg inVideoMsg) {
		
	}
	
	protected void processInLocationMsg(InLocationMsg inLocationMsg) {
		
	}
	
	protected void processInLinkMsg(InLinkMsg inLinkMsg) {
		
	}
	
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
		
	}
	
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		
	}
	
	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
		
	}
	
	protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
		
	}
}
