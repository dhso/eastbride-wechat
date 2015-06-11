/*
 * Copyright (C) 2013, Apexes Network Technology. All rights reserved.
 *
 *       http://www.apexes.net
 *
 */
package frame.sdk.fetion;

import java.io.IOException;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public interface AuthFeedback {
    
    /**
     * 提交验证码。
     * 
     * @param captchaCode 
     */
    void submit(String captchaCode);
    
    /**
     * 取消操作。
     */
    void cancel();
    
    /**
     * 换一个图形验证码。
     * @return 
     * @throws IOException 
     */
    Captcha tryAgain() throws IOException;
    
}
