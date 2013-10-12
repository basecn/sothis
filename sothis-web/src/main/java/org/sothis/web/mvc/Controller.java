package org.sothis.web.mvc;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * controller�ļ򵥷�װ
 * 
 * @author velna
 * 
 */
public interface Controller extends ActionBase {

	public final static String CONTROLLER_SUFFIX = "Controller";

	/**
	 * �õ����controller��nameΪactionName��Action
	 * 
	 * @param actionName
	 * @return
	 */
	Action getAction(String actionName);

	/**
	 * �õ����controller�����е�action
	 * 
	 * @return ����Action��Map��Map��keyΪaction����
	 */
	Map<String, Action> getActions();

	/**
	 * �õ�ʵ��controller����
	 * 
	 * @return
	 */
	Class<?> getControllerClass();

	/**
	 * �õ�controller����
	 * 
	 * @return
	 */
	String getName();

	/**
	 * �õ�controller���ڵİ���������������sothis.controller.packages���õİ���<br>
	 * ����sothis.controller.packages=com.my.app.controller����ô��<br>
	 * com.my.app.controller.MyController�����ؿ��ַ���<br>
	 * com.my.app.controller.user.LoginController������user<br>
	 * com.my.app.controller.user.task.TaskController������user/task
	 * 
	 * @return ��Ե�package����
	 */
	String getPackageName();

	/**
	 * �õ�controller���������ƣ�����package·��
	 * ����sothis.controller.packages=com.my.app.controller����ô��<br>
	 * com.my.app.controller.MyController������/my/<br>
	 * com.my.app.controller.user.LoginController������/user/my/<br>
	 * com.my.app.controller.user.Controller������/user/<br>
	 * com.my.app.controller.user.task.TaskController������/user/task/my/
	 * 
	 * @return
	 */
	String getFullName();

	/**
	 * ָ����ע���Ƿ���controller class��controller package�г���<br>
	 * {@inheritDoc}
	 */
	boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

	/**
	 * �õ���controller class��controller package�г��ֵ�ע��<br>
	 * {@inheritDoc}
	 */
	<T extends Annotation> T[] getAnnotation(Class<T> annotationClass);

	/**
	 * �õ�������controller class��controller package��������ע��<br>
	 * {@inheritDoc}
	 */
	Annotation[] getAnnotations();

	/**
	 * ͬ {@link Action#getAnnotations()}<br>
	 * {@inheritDoc}
	 */
	Annotation[] getDeclaredAnnotations();
}
