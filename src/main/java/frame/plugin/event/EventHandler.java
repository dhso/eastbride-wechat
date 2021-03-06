package frame.plugin.event;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.ExecutorService;

import frame.plugin.event.core.ApplicationEvent;
import frame.plugin.event.core.ApplicationListener;
import frame.plugin.event.utils.ArrayListMultimap;

/**
 * 事件实际处理的类
 * @author L.cm
 * email: 596392912@qq.com
 * site:http://www.dreamlu.net
 * date 2015年4月26日下午10:02:46
 */
@SuppressWarnings("rawtypes")
class EventHandler {

	private final ArrayListMultimap<Type, ApplicationListener> map;
	private final ExecutorService pool;

	public EventHandler(ArrayListMultimap<Type, ApplicationListener> map,
			ExecutorService pool) {
		super();
		this.map = map;
		this.pool = pool;
	}

	/**
	 * 执行发送消息
	 * @param event ApplicationEvent
	 */
	@SuppressWarnings("unchecked")
	public void postEvent(final ApplicationEvent event) {
		Collection<ApplicationListener> listenerList = map.get(event.getClass());
		for (final ApplicationListener listener : listenerList) {
			if (null != pool) {
				pool.execute(new Runnable() {

					@Override
					public void run() {
						listener.onApplicationEvent(event);
					}
				});
			} else {
				listener.onApplicationEvent(event);
			}
		}
	}
}
