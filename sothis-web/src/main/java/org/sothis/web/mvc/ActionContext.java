package org.sothis.web.mvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sothis.core.beans.BeanFactory;
import org.sothis.core.beans.BeanInstantiationException;

/**
 * Action�������ģ��ڲ�����ThreadLocal������˱���Actionִ�й�������Ҫ�ĸ�������
 * 
 * @author velna
 * 
 */
public final class ActionContext {

	public final static String HTTP_REQUEST = "org.sothis.web.mvc.servlet.HttpServletRequest";
	public final static String HTTP_REQUEST_PARAM = "org.sothis.web.mvc.servlet.HttpServletRequestParameters";
	public final static String HTTP_RESPONSE = "org.sothis.web.mvc.servlet.HttpServletResponse";
	public final static String SERVLET_CONTEXT = "org.sothis.web.mvc.servlet.ServeltContext";
	public final static String ACTION = "org.sothis.web.mvc.action.Action";
	public final static String ACTION_PARAMS = "org.sothis.web.mvc.action.Params";
	public final static String LOCALE = "org.sothis.web.mvc.LOCALE";
	public final static String BEAN_FACTORY = "org.sothis.web.mvc.BEAN_FACTORY";
	public final static String SOTHIS_CONFIG = "org.sothis.web.mvc.SOTHIS_CONFIG";
	public final static String MODEL_AND_VIEW_RESOLVER = "org.sothis.web.mvc.MODEL_AND_VIEW_RESOLVER";
	public final static String ACTION_MAPPER = "org.sothis.web.mvc.ACTION_MAPPER";
	public final static String ACTIONS = "org.sothis.web.mvc.ACTIONS";
	public final static String ACTION_URI = "org.sothis.web.mvc.ActionUri";
	public final static String EXCEPTION_HANDLER = "org.sothis.web.mvc.ExceptionHandler";
	public final static String FLASH = "org.sothis.web.mvc.FLAH";

	private final static ThreadLocal<ActionContext> ACTION_CONTEXT = new ThreadLocal<ActionContext>() {
		@Override
		protected ActionContext initialValue() {
			return new ActionContext();
		}
	};

	private final Map<String, Object> context = new HashMap<String, Object>();

	private ActionContext() {
	}

	/**
	 * �����ǰ��������
	 */
	public void clear() {
		ACTION_CONTEXT.remove();
	}

	/**
	 * �õ���ǰ��������
	 * 
	 * @return
	 */
	public static ActionContext getContext() {
		return ACTION_CONTEXT.get();
	}

	/**
	 * ����key��Ӧ������������
	 * 
	 * @param key
	 * @param value
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	public Object set(String key, Object value) {
		return context.put(key, value);
	}

	/**
	 * ��ȡkey��Ӧ������������
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return context.get(key);
	}

	/**
	 * �õ���ǰ�����ĵ�HttpServletRequest����
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) get(HTTP_REQUEST);
	}

	/**
	 * ���õ�ǰ�����ĵ�HttpServletRequest����
	 * 
	 * @param request
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	public HttpServletRequest setRequest(HttpServletRequest request) {
		return (HttpServletRequest) context.put(HTTP_REQUEST, request);
	}

	/**
	 * �õ���ǰ�����ĵ�HttpServletResponse����
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return (HttpServletResponse) get(HTTP_RESPONSE);
	}

	/**
	 * ���õ�ǰ�����ĵ�HttpServletResponse����
	 * 
	 * @param response
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	public HttpServletResponse setResponse(HttpServletResponse response) {
		return (HttpServletResponse) context.put(HTTP_RESPONSE, response);
	}

	/**
	 * �õ���ǰ�����ĵ������������
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object[]> getParameters() {
		return (Map<String, Object[]>) get(HTTP_REQUEST_PARAM);
	}

	/**
	 * ���õ�ǰ�����ĵĵ������������
	 * 
	 * @param parameters
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object[]> setParameters(Map<String, Object[]> parameters) {
		return (Map<String, Object[]>) context.put(HTTP_REQUEST_PARAM, parameters);
	}

	/**
	 * �õ���ǰ�����ĵ�BeanFactory����
	 * 
	 * @return
	 */
	public BeanFactory getBeanFactory() {
		return (BeanFactory) get(BEAN_FACTORY);
	}

	/**
	 * ���õ�ǰ�����ĵ�BeanFactory����
	 * 
	 * @param beanFactory
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	public BeanFactory setBeanFactory(BeanFactory beanFactory) {
		return (BeanFactory) set(BEAN_FACTORY, beanFactory);
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
	public ServletContext setServletContext(ServletContext servletContext) {
		return (ServletContext) set(SERVLET_CONTEXT, servletContext);
	}

	/**
	 * �õ���ǰ�����ĵ�Action����
	 * 
	 * @return
	 */
	public Action getAction() {
		return (Action) get(ACTION);
	}

	/**
	 * ���õ�ǰ�����ĵ�ServletContext����
	 * 
	 * @param servletContext
	 * @return ����ԭʼֵ��û���򷵻�null
	 */
	public Action setAction(Action action) {
		return (Action) set(ACTION, action);
	}

	/**
	 * �õ���ǰ�����ĵ�����
	 * 
	 * @return
	 */
	public Map<String, Object> getContextMap() {
		return Collections.unmodifiableMap(new HashMap<String, Object>(this.context));
	}

	/**
	 * ���õ�ǰ�����ĵ�����
	 * 
	 * @param context
	 * @return ����ԭ�����ĵ�����
	 */
	public Map<String, Object> setContextMap(Map<String, Object> context) {
		if (null == context) {
			throw new IllegalArgumentException("context can not be null!");
		}
		Map<String, Object> ret = new HashMap<String, Object>(this.context);
		this.context.clear();
		this.context.putAll(context);
		return ret;
	}

	public ExceptionHandler getExceptionHandler() {
		return (ExceptionHandler) get(EXCEPTION_HANDLER);
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
				try {
					flash = this.getBeanFactory().getBean(SothisConfig.getConfig().getFlash());
				} catch (BeanInstantiationException e) {
					throw new RuntimeException("error create flash bean", e);
				}
				session.setAttribute(FLASH, flash);
			}
		}
		return flash;
	}
}
