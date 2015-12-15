/**
 * 
 */
/**
 * @author hadong
 *
 */
package frame.plugin.memcached;

import com.jfinal.plugin.IPlugin;
import com.whalin.MemCached.SockIOPool;

/**
 * MemCachedPlugin.
 */
public class MemCachedPlugin implements IPlugin {
	private static SockIOPool sockIOPool;
	private String[] servers;
	private Integer[] weights;
	// set some basic pool settings
	// 5 initial, 5 min, and 250 max conns
	// and set the max idle time for a conn
	// to 6 hours
	private Integer initConn = 5;
	private Integer minConn = 5;
	private Integer maxConn = 250;
	private Integer maxIdle = 1000 * 60 * 60 * 6;
	// set the sleep for the maint thread
	// it will wake up every x seconds and
	// maintain the pool size
	private Long maintSleep = 30L;
	// set some TCP settings
	// disable nagle
	// set the read timeout to 3 secs
	// and don't set a connect timeout
	private Boolean nagle = false;
	private Integer socketTO = 3000;
	private Integer socketConnectTO = 0;

	public MemCachedPlugin() {

	}

	public MemCachedPlugin(String[] servers) {
		this.servers = servers;
	}

	public MemCachedPlugin(String[] servers, Integer[] weights) {
		this.servers = servers;
		this.weights = weights;
	}

	public boolean start() {
		sockIOPool = SockIOPool.getInstance();
		createMemCacheClient();
		MemCacheKit.init(sockIOPool);
		return true;
	}

	private void createMemCacheClient() {
		sockIOPool.setServers(servers);
		if (null != weights) {
			sockIOPool.setWeights(weights);
		}
		sockIOPool.setInitConn(initConn);
		sockIOPool.setMinConn(minConn);
		sockIOPool.setMaxConn(maxConn);
		sockIOPool.setMaxIdle(maxIdle);
		sockIOPool.setMaintSleep(maintSleep);
		sockIOPool.setNagle(nagle);
		sockIOPool.setSocketTO(socketTO);
		sockIOPool.setSocketConnectTO(socketConnectTO);
		if (servers.length > 1) {
			sockIOPool.setFailover(true);
		}
		sockIOPool.initialize();
	}

	public boolean stop() {
		sockIOPool.shutDown();
		return true;
	}

}
