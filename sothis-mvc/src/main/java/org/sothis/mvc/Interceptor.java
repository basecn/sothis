package org.sothis.mvc;

/**
 * �������ӿ�
 * 
 * @author velna
 * 
 */
public interface Interceptor {
	/**
	 * ���ص�ǰ��Action����<br>
	 * ͨ��ʵ������Ҫ����<code>invocation.invoke()</code>
	 * ������ִ����һ��interceptor��action����Ҳ����ѡ��ִ��<code>invocation.invoke()</code>
	 * �����Ƿ�����һ��Object��Ϊ����Action���õĽ��
	 * 
	 * @param invocation
	 * @return
	 * @throws Exception
	 */
	Object intercept(ActionInvocation invocation) throws Exception;

}
