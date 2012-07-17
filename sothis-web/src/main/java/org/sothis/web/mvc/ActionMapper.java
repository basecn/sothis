package org.sothis.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����ӳ��ͽ���action<br>
 * 
 * @author velna
 * 
 */
public interface ActionMapper {
	/**
	 * ����package���ơ�controller���ƺ�action��������action��ΨһID
	 * 
	 * @param packageName
	 *            �����������İ�������ȥ����ǰ׺��ʣ��Ĳ���
	 * @param controllerName
	 *            ȥ����׺Controller��Ĳ���
	 * @param actionName
	 *            ȥ��Action��׺��Ĳ���
	 * @return ����action��ΨһID������null������NullPointerException
	 * @throws IllegalArgumentException
	 *             ����Ϊnull����ַ���ʱ���׳����쳣
	 */
	String map(String controllerPackageName, Class<?> controllerClass, String actionName);

	/**
	 * ������������action��ΨһID
	 * 
	 * @param request
	 * @param response
	 * @return ����null���ʾ�޷�������������404������response�Ѿ���commit
	 * @throws NullPointerException
	 *             request��responseΪnullʱ���׳����쳣
	 */
	String resolve(HttpServletRequest request, HttpServletResponse response);
}
