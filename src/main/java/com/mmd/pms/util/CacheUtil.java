package com.mmd.pms.util;

import com.mmd.pms.common.SpringContextHolder;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Cache工具类
 */
public class CacheUtil {
	
	private static CacheManager cacheManager = SpringContextHolder.getBean("ehCacheManager");

	//系统级缓存
	private static final String CHACHE_NAME = "pmsCache";

	/**
	 * 获取CHACHE_NAME缓存
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(CHACHE_NAME, key);
	}
	
	/**
	 * 写入CHACHE_NAME缓存
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(CHACHE_NAME, key, value);
	}
	
	/**
	 * 从CHACHE_NAME缓存中移除
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(CHACHE_NAME, key);
	}
	
	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	/**
	 * 写入缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 从缓存中移除
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}
	
	/**
	 * 获得一个Cache，没有则创建一个。
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null){
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}
	
}
