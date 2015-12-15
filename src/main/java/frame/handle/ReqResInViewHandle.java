/**
 * 
 */
/**
 * @author hadong
 *
 */
package frame.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class ReqResInViewHandle extends Handler {
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		nextHandler.handle(target, request, response, isHandled);
	}
}