package org.sothis.web.mvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * ��Action�����ļ򵥷�װ
 * 
 * @author velna
 * 
 */
public interface Action extends ActionBase {

	public final static String ACTION_SUFFIX = "Action";

	/**
	 * 
	 * @return
	 */
	Method getActionMethod();

	/**
	 * �õ����Action��Ӧ��Controller����
	 * 
	 * @return
	 */
	Controller getController();

	/**
	 * �õ�Action����
	 * 
	 * @return
	 */
	String getName();

	/**
	 * �õ�Action���������ƣ�����package��controller·��
	 * 
	 * @return
	 */
	String getFullName();

	/**
	 * ָ����ע���Ƿ���action method��controller class��controller package�г���<br>
	 * {@inheritDoc}
	 */
	boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

	/**
	 * �õ���action method��controller class��controller package�г��ֵ�ע��<br> {@inheritDoc}
	 */
	<T extends Annotation> T getAnnotation(Class<T> annotationClass);

	/**
	 * �õ�������action method��controller class��controller package��������ע��<br>
	 * {@inheritDoc}
	 */
	Annotation[] getAnnotations();

	/**
	 * ͬ {@link Action#getAnnotations()}<br> {@inheritDoc}
	 */
	Annotation[] getDeclaredAnnotations();
}
