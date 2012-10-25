package org.sothis.web.mvc.views.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.sothis.core.util.MapUtils;
import org.sothis.core.util.UrlUtils;
import org.sothis.web.mvc.Action;
import org.sothis.web.mvc.ActionContext;
import org.sothis.web.mvc.SothisConfig;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.utility.DeepUnwrap;

/**
 * ��������a��ǩ����url<br>
 * ��ѡ������<br>
 * action: �����������ӵ�action���ƣ�Ĭ��Ϊ��ǰaction������<br>
 * controller: �����������ӵ�controller���ƣ�Ĭ��Ϊ��ǰcontroller������<br>
 * package: �����������ӵ�pacakge���ƣ�Ĭ��Ϊ��ǰpackage������<br>
 * params: �����������ӵĲ�����������һ��freemaker hash��Ҳ������һ���ַ���<br>
 * anchor: �����������ӵ�ê��<br>
 * absolute:
 * ���Ϊtrue����sothis.serverURL������ֵ��Ϊǰ׺�ӵ������ϣ����sothis.serverURLû��ָ����ʹ��http
 * ://localhost:${port}<br>
 * base: ����������Ϊ����ǰ׺���ַ������������ֵ������absoluteֵ<br>
 * notag: ֻ�������ӣ�������a��ǩ����
 * 
 * @author velna
 * 
 */
public class LinkDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Map<String, Object> directiveParams = new HashMap<String, Object>(params);
		ActionContext context = ActionContext.getContext();
		Action action = context.getAction();
		SothisConfig config = SothisConfig.getConfig();

		String actionName = MapUtils.getString(directiveParams, "action", action.getName());

		String controllerName = MapUtils.getString(directiveParams, "controller", action.getController().getName());
		String packageName = MapUtils.getString(directiveParams, "package", action.getController().getPackageName());

		String anchor = MapUtils.getString(directiveParams, "anchor");
		boolean absolute = MapUtils.getBoolean(directiveParams, "absolute", false);
		String base = MapUtils.getString(directiveParams, "base");
		boolean noTag = Boolean
				.valueOf(String.valueOf(DeepUnwrap.unwrap((TemplateModel) directiveParams.get("notag"))));

		StringBuilder url = new StringBuilder();
		if (base == null && absolute) {
			int serverPort = context.getRequest().getServerPort();
			base = config.get("sothis.serverURL", "http://localhost" + (serverPort == 80 ? "" : (":" + serverPort)));
		}
		if (base != null) {
			url.append(base);
		}
		url.append(context.getServletContext().getContextPath());
		if (StringUtils.isNotEmpty(packageName)) {
			url.append('/').append(packageName);
		}
		if (StringUtils.isNotEmpty(controllerName)) {
			url.append('/').append(controllerName);
		}
		if (StringUtils.isNotEmpty(actionName)) {
			url.append('/').append(actionName);
		}

		TemplateModel linkParamsModel = (TemplateModel) directiveParams.get("params");
		if (linkParamsModel instanceof TemplateHashModelEx) {
			Map<String, Object> linkParams = (Map<String, Object>) DeepUnwrap.unwrap(linkParamsModel);
			UrlUtils.appendParams(url, linkParams, config.getCharacterEncoding());
		} else if (null != linkParamsModel) {
			Object linkParams = DeepUnwrap.unwrap(linkParamsModel);
			url.append('?').append(String.valueOf(linkParams));
		}

		if (StringUtils.isNotEmpty(anchor)) {
			url.append('#').append(anchor);
		}
		String urlString = context.getResponse().encodeURL(url.toString());

		directiveParams.remove("action");
		directiveParams.remove("controller");
		directiveParams.remove("package");
		directiveParams.remove("params");
		directiveParams.remove("anchor");
		directiveParams.remove("absolute");
		directiveParams.remove("base");
		directiveParams.remove("notag");

		Writer out = env.getOut();
		if (noTag) {
			out.write(urlString);
		} else {
			out.write("<a href=\"");
			out.write(urlString);
			out.write('"');
			for (Map.Entry<String, Object> entry : directiveParams.entrySet()) {
				out.write(' ');
				out.write(entry.getKey());
				out.write("=\"");
				out.write(String.valueOf(entry.getValue()));
				out.write('"');
			}
			out.write('>');
			body.render(out);
			out.write("</a>");
		}
	}

}
