package org.sothis.core.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;

/**
 * ����һ�����ػ���Ļ����������<br>
 * ���ػ������Google <a
 * href="http://code.google.com/p/guava-libraries">guava</a>��Ŀ��ʵ��<br>
 * ���е�get���ȴӱ��ػ����ж�ȡ��������ػ�����û���ٴ�ʵ�ʻ�����ȡ��<br>
 * ���е�put����put�����ػ��棬��put��ʵ�ʻ���
 * 
 * @author velna
 * 
 */
public class LocalCachedStorage implements Storage {

	private final com.google.common.cache.Cache<String, CacheValue> localCache;
	private final Storage storage;

	/**
	 * ����һ��concurrencyLevelΪ50��soft value�����60��ı��ػ���
	 * 
	 * @param cacheStore
	 */
	public LocalCachedStorage(Storage cacheStore) {
		this.storage = cacheStore;
		this.localCache = CacheBuilder.newBuilder().concurrencyLevel(50).softValues().expireAfterWrite(60, TimeUnit.SECONDS).build();
	}

	public LocalCachedStorage(Storage cacheStore, com.google.common.cache.Cache<String, CacheValue> localCache) {
		this.storage = cacheStore;
		this.localCache = localCache;
	}

	@Override
	public CacheValue get(String key) {
		CacheValue ret = localCache.getIfPresent(key);
		if (null == ret) {
			ret = storage.get(key);
			localCache.put(key, ret);
		}
		return ret;
	}

	@Override
	public Map<String, CacheValue> get(Collection<String> keyList) {
		Set<String> keySet = new HashSet<String>(keyList);
		Map<String, CacheValue> retMap = new HashMap<String, CacheValue>(keySet.size());
		Set<String> actualKeySet = new HashSet<String>(keySet.size());
		for (String k : keySet) {
			CacheValue value = (CacheValue) localCache.getIfPresent(k);
			if (null != value) {
				retMap.put(k, value);
			} else {
				actualKeySet.add(k);
			}
		}
		if (actualKeySet.size() > 0) {
			Map<String, CacheValue> ret = storage.get(actualKeySet);
			if (null != ret) {
				for (Map.Entry<String, CacheValue> entry : ret.entrySet()) {
					CacheValue value = entry.getValue();
					localCache.put(entry.getKey(), value);
					retMap.put(entry.getKey(), value);
				}
			}
		}
		return retMap;
	}

	@Override
	public boolean put(String key, CacheValue value) {
		localCache.put(key, value);
		return storage.put(key, value);
	}

	@Override
	public boolean remove(String key) {
		localCache.invalidate(key);
		return storage.remove(key);
	}

	@Override
	public long size() {
		return storage.size();
	}

}
