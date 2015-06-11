/*
 * Copyright (C) 2013, Apexes Network Technology. All rights reserved.
 *
 *       http://www.apexes.net
 *
 */
package frame.sdk.fetion.client;

import frame.sdk.fetion.sipc.SipcMessage;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public abstract class Dialogue extends Activity {
    
    protected Dialogue(FetionContext context, Controller controller, int callId) {
        super(context, controller, callId);
    }
    
    /**
     * 
     * @param message 
     */
    public abstract void receive(SipcMessage message);
    
}
