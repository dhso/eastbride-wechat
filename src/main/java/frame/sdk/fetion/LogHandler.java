package frame.sdk.fetion;

import frame.sdk.fetion.sipc.SipcMessage;

/**
 *
 * @author HeDYn<hedyn@foxmail.com>
 */
public interface LogHandler {
    
    void transmit(SipcMessage message);
    
    void receive(SipcMessage message);
    
    void error(Class<?> c, String msg, Throwable t);
    
    void debug(Class<?> c, String msg);
    
    void info(Class<?> c, String msg);
    
    void warn(Class<?> c, String msg);
    
}
