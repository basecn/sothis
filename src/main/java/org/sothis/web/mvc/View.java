package org.sothis.web.mvc;

import java.util.Map;


public interface View {
	void render(Object model, ActionInvocation invocation) throws Exception;

	/**
	 * ���� ������params����Ϊnull
	 * 
	 * @param params
	 * @throws IllegalArgumentException
	 */
	void setParams(Map<String, Object> params);
}
