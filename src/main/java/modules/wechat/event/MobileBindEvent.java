package modules.wechat.event;

import frame.plugin.event.core.ApplicationEvent;

/**
 * 如何调用： EventKit.postEvent(new MobileBindEvent(model));
 */
public class MobileBindEvent extends ApplicationEvent {

	private static final long serialVersionUID = 6994987952247306131L;

	public MobileBindEvent(Object source) {
		super(source);
	}
}
