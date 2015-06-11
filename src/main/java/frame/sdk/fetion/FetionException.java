/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */
package frame.sdk.fetion;

/**
 * 飞信异常，是所有飞信异常的基类
 * 
 * @author HeDYn<hedyn@foxmail.com>
 */
public class FetionException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public FetionException(String msg, Throwable e) {
        super(msg, e);
    }

    public FetionException(Throwable e) {
        super(e);
    }

    public FetionException(String msg) {
        super(msg);
    }

    protected FetionException() {
    }
}
