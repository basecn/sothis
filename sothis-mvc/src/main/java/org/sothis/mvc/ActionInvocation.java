package org.sothis.mvc;

/**
 * Action���õķ�װ��������interceptors��ִ�к�action��ִ��<br/>
 * ActionInvocationĿǰ���ᾭ��BeanFactoryʵ��������ֻ��ʹ����DefaultActionInvocation��
 * 
 * @author velna
 * 
 */
public interface ActionInvocation {
	/**
	 * �õ���ǰAction����
	 * 
	 * @return
	 */
	Action getAction();

	/**
	 * �õ���ǰ��Action������
	 * 
	 * @return
	 */
	ActionContext getActionContext();

	/**
	 * ������һ��interceptor�����interceptorȫ��ִ����ϣ��������һ��Action
	 * 
	 * @return
	 * @throws Exception
	 */
	Object invoke() throws Exception;

	/**
	 * �õ���ǰ��controllerʵ��
	 * 
	 * @return
	 */
	Object getControllerInstance();
}
