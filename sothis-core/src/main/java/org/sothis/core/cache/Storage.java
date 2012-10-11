package org.sothis.core.cache;

import java.util.Collection;
import java.util.Map;

/**
 * ����Ĵ洢����
 * 
 * @author velna
 * 
 */
public interface Storage {

	/**
	 * ȡ����ֵΪ{@code key}�Ļ���ֵ
	 * 
	 * @param key
	 * @return
	 */
	CacheValue get(String key);

	/**
	 * ����{@code keys}�õ�����ֵ
	 * 
	 * @param keys
	 * @return keyΪ{@code keys}�е�ֵ��valueΪ��key��Ӧ�Ļ���ֵ
	 */
	Map<String, CacheValue> get(Collection<String> keys);

	/**
	 * ��{@code value}�ŵ�{@code key}��Ӧ�Ļ�����
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	boolean put(String key, CacheValue value);

	/**
	 * �ӻ�����ɾ����Ϊ{@code key}��ֵ
	 * 
	 * @param key
	 * @return
	 */
	boolean remove(String key);

}
