/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */
package frame.sdk.fetion.client.transfer;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
@SuppressWarnings("serial")
public class TransferException extends Exception {

    public TransferException(Throwable cause) {
        super(cause);
    }

    public TransferException(String message) {
        super(message);
    }
    
    public TransferException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferException() {
        super();
    }
}
