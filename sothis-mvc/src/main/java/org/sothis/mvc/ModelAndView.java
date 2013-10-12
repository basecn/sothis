package org.sothis.mvc;

import java.util.Map;

/**
 * Action�ķ���ֵ���ʵ��������ӿڣ���ôʵ�ʵ�model��viewType��viewParams��ʹ������ӿ��ж���ķ�������ȡ��
 * ������ݲ�ͬ��viewʵ��������<br>
 * ���������з���֮���Բ���getXXX��������Ϊ�˲���bean��get��������������Ӱ�첿�ֳ���ķ���
 * 
 * @author velna
 * 
 */
public interface ModelAndView {

	/**
	 * ����ʵ�ʵ�model<br>
	 * �������ֵ���ᴫ��{@link View#render(Object, Map, ActionInvocation)}����
	 * 
	 * @return
	 * @see View#render(Object, Map, ActionInvocation)
	 */
	Object model();

	/**
	 * ����ʵ�ʵ�viewType
	 * 
	 * @return
	 */
	String viewType();

	/**
	 * ����ʵ�ʵ�viewParams<br>
	 * �������ֵ���ᴫ��{@link View#render(Object, Map, ActionInvocation)}����
	 * 
	 * @return
	 * @see View#render(Object, Map, ActionInvocation)
	 */
	Map<String, Object> viewParams();
}
