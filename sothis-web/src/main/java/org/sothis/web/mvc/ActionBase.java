package org.sothis.web.mvc;

import java.lang.annotation.Annotation;

public interface ActionBase {

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

	boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

	<T extends Annotation> T[] getAnnotation(Class<T> annotationClass);

	Annotation[] getAnnotations();

	Annotation[] getDeclaredAnnotations();
}
