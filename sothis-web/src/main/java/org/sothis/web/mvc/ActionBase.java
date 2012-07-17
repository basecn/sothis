package org.sothis.web.mvc;

import java.lang.reflect.AnnotatedElement;

public interface ActionBase extends AnnotatedElement {

	/**
	 * ����
	 * 
	 * @return
	 */
	String getName();

	/**
	 * �������ƣ�����package��controller·��
	 * 
	 * @return
	 */
	String getFullName();

	InterceptorStack getInterceptorStack();

}
