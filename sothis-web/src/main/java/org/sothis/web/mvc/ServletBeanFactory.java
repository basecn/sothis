package org.sothis.web.mvc;

import javax.servlet.ServletContext;

import org.sothis.core.beans.BeanFactory;

/**
 * ����servlet��BeanFactory<br>
 * ʵ��������ӿڵ�BeanFactory����beanFactory����ʵ�����Ժ󣬻����ϵ���{@link #init(ServletContext)}
 * ��������ʼ�����beanFactory
 * 
 * @author velna
 * 
 */
public interface ServletBeanFactory extends BeanFactory {
	/**
	 * ��ʼ��beanFactory
	 * 
	 * @param servletContext
	 *            ��ǰ��ServletContext
	 */
	void init(ServletContext servletContext);
}
