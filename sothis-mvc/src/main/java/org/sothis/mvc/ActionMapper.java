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
	 */
	Object map(Action action);

	/**
	 * ���������ҵ���Ӧ��action
	 * 
	 * @param context
	 * @return ����null���ʾ�޷�������������404������response�Ѿ���commit
	 * @throws NullPointerException
	 *             request��responseΪnullʱ���׳����쳣
	 */
	Action resolve(ActionContext context);
}
