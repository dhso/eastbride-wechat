/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */
package frame.sdk.fetion.client.activity;

import frame.sdk.fetion.FetionException;
import frame.sdk.fetion.Result;
import frame.sdk.fetion.client.ClientHelper;
import frame.sdk.fetion.client.Controller;
import frame.sdk.fetion.client.Dialogue;
import frame.sdk.fetion.client.FetionContext;
import frame.sdk.fetion.sipc.RequestMessage;
import frame.sdk.fetion.sipc.ResponseMessage;
import frame.sdk.fetion.sipc.Sipc;
import frame.sdk.fetion.sipc.SipcMessage;
import frame.sdk.fetion.user.Buddy;
import frame.sdk.fetion.util.XmlElement;

/**
 * 
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public class ChatDialogue extends Dialogue {

    private Buddy buddy;

    public ChatDialogue(FetionContext context, Controller controller, int callId, Buddy buddy) {
        super(context, controller, callId);
        this.buddy = buddy;
    }

    @Override
    public void receive(SipcMessage message) {
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public Result sendMessage(String msg) throws FetionException {
        if (ClientHelper.isMobileUri(buddy.getUri())) {
            return sendSMSMessage(msg);
        }
        return sendChatMessage(msg);
    }

    public Result sendSMSMessage(String msg) throws FetionException {
        return submitRequest(MessageHelper.createMsgRequest(buddy, msg, true));
    }

    public Result sendChatMessage(String msg) throws FetionException {
        return submitRequest(MessageHelper.createMsgRequest(buddy, msg, false));
    }

    /**
     * 
     *
     * @param request
     * @return
     * @throws FetionException
     */
    private Result submitRequest(RequestMessage request) throws FetionException {
        ResponseMessage response = submit(request);
        int status = response.getStatus();
        switch (status) {
            case Sipc.STATUS_ACTION_OK:
                return new Result(status,
                        response.getStatusMessage(),
                        Result.Type.SUCCESS, "发送成功。");
                
            case Sipc.STATUS_SEND_SMS_OK:
                updateQuotaFrequency(response.getBody());
                return new Result(status,
                        response.getStatusMessage(),
                        Result.Type.SUCCESS, "已发送到对方手机。");
            default:
                return new Result(status,
                        response.getStatusMessage(),
                        Result.Type.FAILURE, "发送失败。");
        }
    }
    
    private void updateQuotaFrequency(String body) {
        //<results><quota-frequency>
        //<frequency name="send-sms" day-count="5" month-count="17"/>
        //</quota-frequency></results>
        try {
            XmlElement xml = new XmlElement();
            xml.parseString(body);
            getContext().getUserInfo().getQuota().update(getController(), xml);
        } catch (Exception ex) {
            getContext().getLogHandler().error(ChatDialogue.class, body, ex);
        }
    }
}
