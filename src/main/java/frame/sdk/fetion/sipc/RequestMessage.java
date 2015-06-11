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
public class RequestMessage extends SipcMessage {
    
    private String acceptor;
    
    public RequestMessage(String method) {
        this(method, "fetion.com.cn");
    }
    
    public RequestMessage(String method, String acceptor) {
        this.acceptor = acceptor; 
        setMethod(method);
    }
    
    public String getAcceptor() {
        return acceptor;
    }
    
    @Override
    protected String getHeadline() {
        return getMethod() + " " + getAcceptor() + " " + Sipc.SIPC_VERSION;
    }
}
