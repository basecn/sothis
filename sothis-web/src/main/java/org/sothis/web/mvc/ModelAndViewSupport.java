package org.sothis.web.mvc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.sothis.web.mvc.annotation.Ignore;

public class ModelAndViewSupport implements ModelAndView, Serializable {

	private static final long serialVersionUID = 2142558258767601308L;
	private String viewType = ModelAndViewResolver.DEFAULT_VIEW_TYPE;
	private Map<String, Object> viewParams;

	public Object model() {
		return this;
	}

	public String viewType() {
		return viewType;
	}

	public Map<String, Object> viewParams() {
		return viewParams;
	}

	/**
	 * ��ʱ�ض���(302)
	 * 
	 * @param location
	 *            ��Ҫ�ض����action��url
	 * @return
	 */
	public final ModelAndViewSupport redirect(String location) {
		this.setViewType("redirect");
		this.setViewParam("location", location);
		return this;
	}

	/**
	 * �����ض���(301)
	 * 
	 * @param location
	 *            ��Ҫ�ض����action��url
	 * @return
	 */
	public final ModelAndViewSupport redirectPermanently(String location) {
		this.setViewParam("statusCode", HttpServletResponse.SC_MOVED_PERMANENTLY);
		this.setViewType("redirect");
		this.setViewParam("location", location);
		return this;
	}

	/**
	 * ҳ��δ�ҵ�(404)
	 * 
	 * @param path
	 *            404ҳ���·��
	 * @return
	 */
	public final ModelAndViewSupport notFound(String path) {
		this.setViewParam("status", HttpServletResponse.SC_NOT_FOUND);
		this.setViewParam("path", path);
		return this;
	}

	/**
	 * ҳ��δ�ҵ�(404)��Ĭ��404ҳ���·��Ϊ /404
	 * 
	 * @return
	 */
	public final ModelAndViewSupport notFound() {
		return notFound("/404");
	}

	/**
	 * ת��pathָ����ҳ��
	 * 
	 * @param path
	 * @return
	 */
	public final ModelAndViewSupport forward(String path) {
		this.setViewParam("path", path);
		return this;
	}

	public final Flash flash() {
		return ActionContext.getContext().getFlash();
	}

	@Ignore
	public final void setViewType(final String viewType) {
		this.viewType = viewType;
	}

	@Ignore
	public final void setViewParams(final Map<String, Object> viewParams) {
		this.viewParams = viewParams;
	}

	public final void setViewParam(final String paramName, Object paramValue) {
		initViewParams();
		viewParams.put(paramName, paramValue);
	}

	public final Object getViewParam(final String paramName) {
		if (null == viewParams) {
			return null;
		}
		return viewParams.get(paramName);
	}

	private synchronized void initViewParams() {
		if (null == viewParams) {
			viewParams = new HashMap<String, Object>();
		}
	}
}
