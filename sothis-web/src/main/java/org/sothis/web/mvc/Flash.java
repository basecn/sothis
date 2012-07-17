package org.sothis.web.mvc;

import java.io.Serializable;

/**
 * ����Flash�еĶ�����������ֻ��һ��flash���ڵڶ���flash��ɾ��<br>
 * ��sothis�У�ÿһ��http�����൱��һ��flash
 * 
 * @author velna
 * 
 */
public interface Flash extends Serializable, Iterable<String> {
	Object setAttribute(String key, Object value);

	Object getAttribute(String key);

	Object removeAttribute(String key);

	boolean containsAttribute(String key);

	/**
	 * ִ��һ��flash
	 */
	void flash();
}
