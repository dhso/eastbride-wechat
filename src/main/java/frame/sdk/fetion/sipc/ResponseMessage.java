/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */

package frame.sdk.fetion.sipc;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public class ResponseMessage extends SipcMessage {
    
    /**
	 * 回复状态代码
	 */
	private int status;
	
	/**
	 * 回复状态说明
	 */
	private String statusMessage;
    
    /**
     * 该回复对应的请求
     */
    private RequestMessage requestMessage;
    
    /**
     * 
     * @param status
     * @param statusMessage 
     */
    public ResponseMessage(int status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage; 
    }
    
    public int getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(RequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }
    
    @Override
    protected String getHeadline() {
        return Sipc.SIPC_VERSION + " " + getStatus() + " " + getStatusMessage();
    }

}
