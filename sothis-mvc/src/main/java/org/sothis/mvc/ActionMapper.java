package org.sothis.mvc;

/**
 * ����ӳ��ͽ���action<br>
 * 
 * @author velna
 * 
 */
public interface ActionMapper {

	/**
	 * ����action����action��ΨһID
	 * 
	 * @param action
	 *            ��Ҫӳ���action
	 * 
	 * @return ����action��ΨһID������null������NullPointerException
	 * @throws IllegalArgumentException
	 *             ����Ϊnull����ַ���ʱ���׳����쳣
	 */
	Object map(Action action);

	/**
	 * ���������actionStore���ҵ���Ӧ��action
	 * 
	 * @param request
	 * @param response
	 * @param store
	 * @return ����null���ʾ�޷�������������404������response�Ѿ���commit
	 * @throws NullPointerException
	 *             request��responseΪnullʱ���׳����쳣
	 */
	Action resolve(Object request, Object response, ApplicationContext store);
}
