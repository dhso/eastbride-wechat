/*
 * Copyright (C) 2012, Apexes Network Technology. All rights reserved.
 * 
 *       http://www.apexes.net
 * 
 */

package frame.sdk.fetion.sipc;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public class SipcMessageWriter {
    
    private OutputStream out;
    
    public SipcMessageWriter(OutputStream out) {
        this.out = out;
    }
    
    public void close() throws IOException {
        out.close();
    }
    
    public void write(SipcMessage message) throws IOException {
        out.write(message.getText().getBytes());
    }
}
