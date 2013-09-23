package org.sothis.core.cache;

import java.util.Collection;
import java.util.Map;

import org.sothis.core.util.Closure;

/**
 * ����ӿڡ�<br>
 * nullֵҲ�ᱻ���뵽������ȥ��
 * 
 * @author velna
 * 
 */
public interface Cache {
	/**
	 * �ӻ�����ȡ����Ϊ{@code key}��ֵ
	 * 
	 * @param key
	 *            ����ļ�ֵ
	 * @return {@code key}��Ӧ�Ļ���ֵ
	 */
	<V> V get(String key);

	/**
	 * �ӻ�����ȡ����Ϊ{@code key}��ֵ������������Ҳ����������{@code closure}�����ɻ��棬������ֵ���µ�������ȥ��
	 * 
	 * @param key
	 *            ����ļ�ֵ
	 * @param closure
	 *            ����{@code key}��Ӧ�Ļ���ֵ�ıհ�
	 * @return {@code key}��Ӧ�Ļ���ֵ
	 */
	<V> V get(String key, Closure<CacheValue, String> closure);

	/**
	 * ����һ���ֵ���õ���Щ������Ӧ�Ļ��档{@code extraKey}��{@code keys}�е�ÿһ��ֵ����ƴ�Ӻ���Ϊʵ������ȡ����ļ�ֵ��
	 * 
	 * @param extraKey
	 *            ����ļ�ֵ
	 * @param keys
	 *            ��Ҫ���ҵ�key����
	 * @return ����һ��Map��keyΪ{@code keys}�е�һ��ֵ��valueΪ��key��Ӧ�Ļ���ֵ
	 */
	<V> Map<String, V> get(String extraKey, Collection<String> keys);

	/**
	 * ����һ���ֵ���õ���Щ������Ӧ�Ļ��档{@code extraKey}��{@code keys}�е�ÿһ��ֵ����ƴ�Ӻ���Ϊʵ������ȡ����ļ�ֵ��<br>
	 * ����������Ҳ���{@code keys}�е�һ���������棬��Щkey��ͨ������{@code closure}�����ɡ�
	 * 
	 * @param extraKey
	 *            ����ļ�ֵ
	 * @param keys
	 *            ��Ҫ���ҵ�key����
	 * @param closure
	 * @return ����һ��Map��keyΪ{@code keys}�е�һ��ֵ��valueΪ��key��Ӧ�Ļ���ֵ
	 */
	<V> Map<String, V> get(String extraKey, Collection<String> keys, Closure<Map<String, CacheValue>, Collection<String>> closure);

	/**
	 * ��{@code value}�ŵ�{@code key}��Ӧ�Ļ����С�
	 * 
	 * @param key
	 *            �����
	 * @param value
	 *            ����ֵ
	 * @param timeToLive
	 *            ����Ĵ��ʱ�䣬����Ϊ��λ
	 * @return �ɹ�����true�����򷵻�false
	 */
	boolean put(String key, Object value, long timeToLive);

	/**
	 * ��{@code cacheValue}�ŵ�{@code key}��Ӧ�Ļ����С�
	 * 
	 * @param key
	 *            �����
	 * @param cacheValue
	 *            ����ֵ
	 * @return �ɹ�����true�����򷵻�false
	 */
	boolean put(String key, CacheValue cacheValue);

	/**
	 * �ӻ�����ɾ����ֵΪ{@code key}�Ļ��档
	 * 
	 * @param key
	 *            Ҫɾ���Ļ����ֵ
	 * @return �ɹ�����true�����򷵻�false
	 */
	boolean remove(String key);

	/**
	 * �ӻ�����ɾ����ֵΪ{@code extraKey}��{@code key}�Ļ��档
	 * 
	 * @param extraKey
	 * @param key
	 * @return �ɹ�����true�����򷵻�false
	 */
	boolean remove(String extraKey, String key);

	/**
	 * �õ������еļ�¼����
	 * 
	 * @return
	 */
	long size();

	/**
	 * �õ������ͳ�����
	 * 
	 * @return
	 */
	CacheStat stats();

	/**
	 * �õ�����Ĵ洢����
	 * 
	 * @param storage
	 */
	Storage getStorage();
}
