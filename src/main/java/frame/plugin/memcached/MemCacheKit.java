package frame.plugin.memcached;

import java.util.Date;
import java.util.Map;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemCacheKit {

	protected static MemCachedClient mcc = new MemCachedClient();
	private static SockIOPool sockIOPool;

	static void init(SockIOPool sockIOPool) {
		MemCacheKit.sockIOPool = sockIOPool;
	}

	public static MemCachedClient getMemCachedClient() {
		return mcc;
	}

	public static SockIOPool getSockIOPool() {
		return sockIOPool;
	}

	public static void put(String key, Object value) {
		mcc.set(key, value, 24 * 60 * 60);
	}

	public static void put(String key, Object value, Long expirySecond) {
		mcc.set(key, value, new Date(1000 * expirySecond));
	}

	public static Object get(String key) {
		return mcc.get(key);
	}

	public static Map<String, Object> getKeys(String[] keys) {
		return mcc.getMulti(keys);
	}

	public static Boolean remove(String key) {
		return mcc.delete(key);
	}

	public static Boolean removeAll(String cacheName) {
		return mcc.flushAll();
	}
}
