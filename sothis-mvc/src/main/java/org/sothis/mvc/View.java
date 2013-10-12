package org.sothis.mvc;

import java.io.IOException;

/**
 * view�ӿڣ����е�view����Ҫʵ������ӿ�
 * 
 * @author velna
 * 
 */
public interface View {
	/**
	 * ʹ�õ�ǰ��view��Ⱦ������
	 * 
	 * @param mav
	 * @param invocation
	 *            ��ǰ��action����
	 * @throws IOException
	 * @throws ViewRenderException
	 */
	void render(ModelAndView mav, ActionInvocation invocation) throws IOException, ViewRenderException;
}
