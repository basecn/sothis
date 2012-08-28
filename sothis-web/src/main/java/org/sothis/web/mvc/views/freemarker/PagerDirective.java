/**
 * 
 */
package org.sothis.web.mvc.views.freemarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.sothis.core.util.MapUtils;
import org.sothis.core.util.UrlUtils;
import org.sothis.web.mvc.ActionContext;

import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author wangxinghai
 * 
 */
public class PagerDirective implements TemplateDirectiveModel {

	@SuppressWarnings({ "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		ActionContext actionContext = ActionContext.getContext();
		// name
		String name = MapUtils.getString(params, "name", "pager");

		// name
		String anchor = MapUtils.getString(params, "anchor");

		// controller
		String controller = MapUtils.getString(params, "controller", actionContext.getAction().getController().getName());

		// action
		String action = MapUtils.getString(params, "action", actionContext.getAction().getName());

		// action
		String theme = MapUtils.getString(params, "theme", "all");

		SimpleNumber num;
		// currentPage
		num = (SimpleNumber) MapUtils.getObject(params, "currentPage");
		if (null == num) {
			throw new TemplateException("currentPage can not be null of pager", env);
		}
		int currentPage = num.getAsNumber().intValue();

		// totalPages
		num = (SimpleNumber) MapUtils.getObject(params, "totalPages");
		if (null == num) {
			throw new TemplateException("totalPages can not be null of pager", env);
		}
		int totalPages = num.getAsNumber().intValue();

		// pagerCount
		int pagerCount = 6;
		num = (SimpleNumber) MapUtils.getObject(params, "pagerCount");
		if (null != num) {
			pagerCount = num.getAsNumber().intValue();
		}

		Map<String, Object[]> requestParams = actionContext.getParameters();
		Map<String, Object[]> allParams = filterPagerParams(new HashMap<String, Object[]>(requestParams));

		Template template = env.getConfiguration().getTemplate("/ftl/pager.ftl");
		Map<String, Object> templateContext = new HashMap<String, Object>();

		List<String> pages = new ArrayList<String>(pagerCount);
		List<Integer> pageIndex = new ArrayList<Integer>(pagerCount);
		int start = currentPage - pagerCount / 2;
		start = start > 0 ? start : 1;
		int end = start + pagerCount;
		end = end > totalPages ? totalPages + 1 : end;
		for (int i = start; i < end; i++) {
			pageIndex.add(i);
			if (currentPage == i) {
				pages.add("currentPage");
			} else {
				pages.add(this.buildPagerUrl(actionContext, name, controller, action, allParams, i, anchor));
			}
		}
		templateContext.put("theme", theme);
		templateContext.put("currentPage", currentPage);
		templateContext.put("totalPages", totalPages);
		templateContext.put("firstPageUrl", this.buildPagerUrl(actionContext, name, controller, action, allParams, 1, anchor));
		templateContext.put("prePageUrl",
				this.buildPagerUrl(actionContext, name, controller, action, allParams, currentPage - 1, anchor));
		templateContext.put("nextPageUrl",
				this.buildPagerUrl(actionContext, name, controller, action, allParams, currentPage + 1, anchor));
		templateContext.put("currentPageUrl",
				this.buildPagerUrl(actionContext, name, controller, action, allParams, currentPage, anchor));
		templateContext.put("lastPageUrl",
				this.buildPagerUrl(actionContext, name, controller, action, allParams, totalPages, anchor));
		templateContext.put("pageUrls", pages);
		templateContext.put("pageIndex", pageIndex);

		template.process(templateContext, env.getOut());
	}

	private String buildPagerUrl(ActionContext actionContext, String name, String controller, String action,
			Map<String, Object[]> allParams, int pageIndex, String anchor) throws IOException {
		allParams.put(name + ".currentPage", new String[] { String.valueOf(pageIndex) });
		String params = UrlUtils.appendParams("", allParams);
		HttpServletResponse httpResponse = actionContext.getResponse();
		StringBuilder url = new StringBuilder();
		url.append('/').append(controller).append('/').append(action).append(params);

		String ret = httpResponse.encodeURL(url.toString());
		String contextPath = actionContext.getServletContext().getContextPath();
		if (!ret.startsWith(contextPath)) {
			ret = contextPath + ret;
		}
		if (null != anchor) {
			ret = ret + "#" + anchor;
		}
		return ret;
	}

	/**
	 * ������Ը��������������pager�Ĳ������й���
	 * 
	 * @param params
	 * @return
	 */
	protected Map<String, Object[]> filterPagerParams(Map<String, Object[]> params) {
		return params;
	}

}
