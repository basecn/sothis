package org.sothis.mvc;

import java.lang.annotation.Annotation;
import java.util.List;

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

	List<Class<Interceptor>> getInterceptors();

	boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

	<T extends Annotation> T[] getAnnotation(Class<T> annotationClass);

	Annotation[] getAnnotations();

	Annotation[] getDeclaredAnnotations();
}
