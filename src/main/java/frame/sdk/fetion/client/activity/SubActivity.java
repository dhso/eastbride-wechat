/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */

package frame.sdk.fetion.client.activity;

import frame.sdk.fetion.FetionException;
import frame.sdk.fetion.client.Activity;
import frame.sdk.fetion.client.Controller;
import frame.sdk.fetion.client.FetionContext;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public class SubActivity extends Activity {
    
    public SubActivity(FetionContext context, Controller controller, int callId) {
        super(context, controller, callId);
    }

    /**
     * 发送 SUB PresenceV4 请求。
     */
    public void submitSubPresence() {
        try {
            submit(MessageHelper.createSubPresenceRequest());
        } catch (FetionException ex) {
            getContext().getLogHandler().error(SubActivity.class, "发送请求：SUB PresenceV4", ex);
        }
    }
}
