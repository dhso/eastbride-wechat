/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */
package frame.sdk.fetion;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public interface AuthSupportable {
    
    /**
     * 需要输入图形验证码。
     * 
     * @param captcha 包含验证图片等信息的对象。
     * @param feedback 验证回执。
     */
    void needAuth(Captcha captcha, AuthFeedback feedback);
    
}
