package org.sothis.core.cache;

/**
 * ������ʽ�Ļ���ʵ��
 * 
 * @author velna
 * 
 */
public class RegionalCache extends BasicCache {
	private final String region;

	/**
	 * ����һ��������Ϊ{@code region}�Ļ���
	 * 
	 * @param region
	 * @param storage
	 */
	public RegionalCache(String region, Storage storage) {
		super(region, storage);
		this.region = region;
	}

	@Override
	protected String buildCacheKey(Object... keys) {
		StringBuilder ret = new StringBuilder();
		ret.append(region).append('_');
		for (int i = 0; i < keys.length; i++) {
			ret.append(keys[i]);
			if (i < keys.length - 1) {
				ret.append('_');
			}
		}
		return ret.toString();
	}

	/**
	 * ������
	 * 
	 * @return
	 */
	public String getRegion() {
		return region;
	}

}
