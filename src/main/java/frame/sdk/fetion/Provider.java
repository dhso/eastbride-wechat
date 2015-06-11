/*
 * Copyright (C) 2013, Apexes Network Technology. All rights reserved.
 *
 *       http://www.apexes.net
 *
 */
package frame.sdk.fetion;

import frame.sdk.fetion.util.XmlElement;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public interface Provider {
    
    /**
     * 
     * @return 
     */
    XmlElement readSystemConfig();
    
    /**
     * 
     * @return 
     */
    UserInfo readUserInfo();
    
}
