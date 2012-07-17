package org.sothis.web.mvc;

import org.sothis.core.beans.BeanFactory;

/**
 * model��view�Ľ�����<br>
 * ���ݵ�ǰ��actionִ�н������������Ӧ��model��view
 * 
 * @author velna
 * 
 */
public interface ModelAndViewResolver {

	/**
	 * ȱʡview������
	 */
	static final String DEFAULT_VIEW_TYPE = "org.sothis.web.mvc.view.DEFAULT_VIEW_TYPE";

	/**
	 * ����model��view��view����ʹ��{@link BeanFactory#getBean(Class)}��������
	 * 
	 * @param actionResult
	 * @param invocation
	 * @return
	 * @throws ViewCreationException
	 *             beanFactory��ʼ���쳣��û��ע��view���͡�
	 *             û������defaultView��actionResultΪnullʱ���׳����쳣
	 */
	ResolvedModelAndView resolve(Object actionResult, ActionInvocation invocation) throws ViewCreationException;

	/**
	 * ����typeName�õ���Ӧ��viewʵ��
	 * 
	 * @param typeName
	 * @return
	 */
	View getView(String typeName, ActionInvocation invocation) throws ViewCreationException;
}
