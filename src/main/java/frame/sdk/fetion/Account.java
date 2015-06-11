/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */
package frame.sdk.fetion;

import frame.sdk.fetion.client.ClientHelper;
import frame.sdk.fetion.user.Presence;
import frame.sdk.fetion.util.ConvertHelper;
import frame.sdk.fetion.util.DigestHelper;

/**
 * 飞信账户。
 * 
 * @author HeDYn<hedyn@foxmail.com>
 */
public class Account {
   
    /**
     * 用户的URI。
     * 格式为：sip:123456789@fetion.com.cn;p=1234 或 tel:13901234567
     */
    private String uri;
    
    /**
     * 飞信号
     */
    private int userId;
    
    private String sid;
    
    /**
     * 手机号码
     */
    private long mobileNo;
    
    private String password;
    
    private Presence presence = Presence.ONLINE;
    
    /**
	 * 用于解密Credential的key
	 */
	private byte[] aesKey;
	
	/**
	 * 用于解密Credential的iv
	 */
	private byte[] aesIV;
    
    Account(long mobileNo, int userId, String uri) {
        this.mobileNo = mobileNo;
        this.userId = userId;
        this.uri = uri;
        sid = ClientHelper.getSidFromUri(uri);
        aesKey = DigestHelper.createAESKey();
		aesIV  = ConvertHelper.hexString2ByteNoSpace("00399F3D125DB5530AB5E000D6B0F45A");	//固定值
    }
    
    public int getUserId() {
        return userId;
    }
    
    public long getMobileNo() {
        return mobileNo;
    }

    public String getUri() {
        return uri;
    }

    public String getSid() {
        return sid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public byte[] getAesKey() {
        return aesKey;
    }

    public byte[] getAesIV() {
        return aesIV;
    }
    
    @Override
    public String toString() {
        return "Account{" + "uri=" + uri 
                + ", userId=" + userId 
                + ", sid=" + sid 
                + ", mobileNo=" + mobileNo 
                + ", password=" + password 
                + ", presence=" + presence + '}';
    }
}
