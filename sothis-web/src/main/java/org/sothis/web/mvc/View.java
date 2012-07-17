package org.sothis.web.mvc;

import java.io.IOException;

import javax.servlet.ServletException;

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
	 * @throws ServletException
	 */
	void render(ModelAndView mav, ActionInvocation invocation) throws IOException, ServletException;
}
