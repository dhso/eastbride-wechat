package modules.wechat.entity;

import java.io.Serializable;

/**
 * 微信配置
 * 
 * @author hadong
 * 
 */
public class WxProp implements Serializable {
	private static final long serialVersionUID = -7556424561335197375L;

	/**
	 * 编号
	 */
	private String id;
	/**
	 * token
	 */
	private String token = null;
	/**
	 * appId
	 */
	private String appId = null;
	/**
	 * appSecret
	 */
	private String appSecret = null;
	/**
	 * 消息加密与否
	 */
	private boolean messageEncrypt = false;
	/**
	 * 加密Key
	 */
	private String encodingAesKey = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	public boolean isMessageEncrypt() {
		return messageEncrypt;
	}

	public void setMessageEncrypt(boolean messageEncrypt) {
		this.messageEncrypt = messageEncrypt;
	}

}
