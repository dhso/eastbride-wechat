package modules.wechat.listener;

import modules.wechat.event.MobileBindEvent;
import frame.plugin.event.core.ApplicationListener;
import frame.plugin.event.core.Listener;

//注解标记，切勿忘记
@Listener
public class MobileBindListener implements ApplicationListener<MobileBindEvent> {

	@Override
	public void onApplicationEvent(MobileBindEvent event) {
		// TODO Auto-generated method stub

	}

}
