/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */

package frame.sdk.fetion.client;

import frame.sdk.fetion.Account;
import frame.sdk.fetion.AuthSupportable;
import frame.sdk.fetion.LogHandler;
import frame.sdk.fetion.SystemConfig;
import frame.sdk.fetion.UserInfo;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public interface FetionContext {
    
    /**
     * 返回客户端的机器码。
     * @return 
     */
    String getMachineCode();
    
    /**
     * 
     * @return 
     */
    Account getAccount();
    
    /**
     * 
     * @return 
     */
    AuthSupportable getAuthSupportable();
    
    /**
     * 返回系统配置。
     * @return 
     */
    SystemConfig getSystemConfig();
    
    /**
     * 
     * @return 
     */
    UserInfo getUserInfo();
    
    /**
     * 
     * @return 
     */
    CmccMobileValidator getCmccMobileValidator();
    
    /**
     * 
     * @return 
     */
    LogHandler getLogHandler();

}
