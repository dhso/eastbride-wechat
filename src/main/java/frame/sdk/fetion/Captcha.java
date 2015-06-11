/*
 * Copyright (C) 2013, Apexes Network Technology. All rights reserved.
 *
 *       http://www.apexes.net
 *
 */
package frame.sdk.fetion;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public interface Captcha {
    
    /**
     * 验证图片数据
     * @return 
     */
    byte[] getImageData();
    
    /**
     * 验证图片编号
     * @return 
     */
    String getImageId();

    /**
     * 验证原因
     * @return 
     */
    String getText();

    /**
     * 提示信息
     * @return 
     */
    String getTips();
    
    /**
     * 算法
     * @return 
     */
    String getVerifyAlgorithm();
    
    /**
     * 
     * @return 
     */
    String getVerifyType();

    /**
     * 当前图片验证失败的次数
     * @return 
     */
    int getFailCount();
}
