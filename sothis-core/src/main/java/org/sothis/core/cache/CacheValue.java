package org.sothis.core.cache;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * ����ֵ��ʵ�ʷ��뻺���Ϊ����ࡣ
 * 
 * @author velna
 * 
 */
public class CacheValue implements Externalizable {

	protected long timeToLive;
	protected Object value;

	/**
	 * ����һ��ֵΪ{@code value}�����ʱ��Ϊ{@code timeToLive}��Ļ���
	 * 
	 * @param value
	 * @param timeToLive
	 *            ��λ����
	 */
	public CacheValue(Object value, long timeToLive) {
		this.value = value;
		this.timeToLive = timeToLive;
	}

	/**
	 * ʵ�ʻ����ֵ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <V> V getValue() {
		return (V) value;
	}

	/**
	 * ������ʱ��
	 * 
	 * @return ��λ����
	 */
	public long getTimeToLive() {
		return timeToLive;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(timeToLive);
		out.writeObject(value);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.timeToLive = in.readLong();
		this.value = in.readObject();
	}

}
