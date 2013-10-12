package org.sothis.web.mvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sothis.mvc.Action;
import org.sothis.mvc.ActionContext;
import org.sothis.mvc.ApplicationContext;

public class WebActionContext implements ActionContext {

	public final static String FLASH = "org.sothis.web.mvc.FLAH";
	public final static String SERVLET_CONTEXT = "org.sothis.web.mvc.ServeltContext";
	public final static String ACTION_URI = "org.sothis.web.mvc.ACTION_URI";
	public final static String REQUEST_PARAM = "org.sothis.web.mvc.REQUEST_PARAM";

	protected final static ThreadLocal<WebActionContext> ACTION_CONTEXT = new ThreadLocal<WebActionContext>() {
		@Override
		protected WebActionContext initialValue() {
			return new WebActionContext();
		}
	};

	private WebActionContext() {
	}

	protected final Map<String, Object> context = new HashMap<String, Object>();

	/**
	 * �õ���ǰ��������
	 * 
	 * @return
	 */
	public static WebActionContext getContext() {
		return ACTION_CONTEXT.get();
	}

	/**
	 * �õ���ǰ�����ĵ�HttpServletRequest����
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) get(REQUEST);
	}

	/**
	 * ���õ�ǰ�����ĵ�HttpServletRequest����
	 * 
	 * @param request
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	public void setRequest(Object request) {
		context.put(REQUEST, request);
	}

	/**
	 * �õ���ǰ�����ĵ�HttpServletResponse����
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return (HttpServletResponse) get(RESPONSE);
	}

	/**
	 * ���õ�ǰ�����ĵ�HttpServletResponse����
	 * 
	 * @param response
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	public void setResponse(Object response) {
		context.put(RESPONSE, response);
	}

	/**
	 * �õ���ǰ�����ĵ�ServletContext����
	 * 
	 * @return
	 */
	public ServletContext getServletContext() {
		return (ServletContext) get(SERVLET_CONTEXT);
	}

	/**
	 * ���õ�ǰ�����ĵ�ServletContext����
	 * 
	 * @param servletContext
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	public void setServletContext(ServletContext servletContext) {
		put(SERVLET_CONTEXT, servletContext);
	}

	/**
	 * �õ���ǰ�����ĵ������������
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object[]> getParameters() {
		return (Map<String, Object[]>) get(REQUEST_PARAM);
	}

	/**
	 * ���õ�ǰ�����ĵĵ������������
	 * 
	 * @param parameters
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object[]> setParameters(Map<String, Object[]> parameters) {
		return (Map<String, Object[]>) context.put(REQUEST_PARAM, parameters);
	}

	/**
	 * �õ���ǰ�������е�Flash����û����ᴴ��һ���µ�Flash����
	 * 
	 * @return
	 */
	public Flash getFlash() {
		return getFlash(true);
	}

	/**
	 * �õ���ǰ�������е�Flash����
	 * 
	 * @param create
	 *            ����ǰ��������û��Flash����ʱ�Ƿ񴴽�һ���µĶ���trueΪ������falseΪ������
	 * @return
	 */
	public Flash getFlash(boolean create) {
		Flash flash = null;
		HttpSession session = this.getRequest().getSession(create);
		if (session != null) {
			flash = (Flash) session.getAttribute(FLASH);
			if (null == flash && create) {
				flash = this.getApplicationContext().getBeanFactory().getBean(this.getConfiguration().getFlash());
				session.setAttribute(FLASH, flash);
			}
		}
		return flash;
	}

	@Override
	public Action getAction() {
		return (Action) get(ACTION);
	}

	@Override
	public void setAction(Action action) {
		put(ACTION, action);
	}

	@Override
	public Object put(String key, Object value) {
		return context.put(key, value);
	}

	@Override
	public Object get(String key) {
		return context.get(key);
	}

	@Override
	public Map<String, Object> getContextMap() {
		return Collections.unmodifiableMap(this.context);
	}

	@Override
	public Map<String, Object> setContextMap(Map<String, Object> context) {
		if (null == context) {
			throw new IllegalArgumentException("context can not be null!");
		}
		Map<String, Object> ret = new HashMap<String, Object>(this.context);
		this.context.clear();
		this.context.putAll(context);
		return ret;
	}

	@Override
	public void clear() {
		ACTION_CONTEXT.remove();
	}

	public WebConfiguration getConfiguration() {
		return (WebConfiguration) this.getApplicationContext().getConfiguration();
	}

	@Override
	public ApplicationContext getApplicationContext() {
		return (ApplicationContext) get(APPLICATION_CONTEXT);
	}
}
