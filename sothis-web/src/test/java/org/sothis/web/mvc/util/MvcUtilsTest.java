/**
 * copyright (c) by fangjia 2011
 */
package org.sothis.web.mvc.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sothis.core.beans.BeanInstantiationException;
import org.sothis.mvc.ConfigurationException;
import org.sothis.mvc.Controller;
import org.sothis.mvc.DefaultController;
import org.sothis.web.mvc.MockActionInvocation;
import org.sothis.web.mvc.MockBeanFactory;
import org.sothis.web.mvc.SothisFactory;
import org.sothis.web.mvc.WebActionContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * ����MvcUtils������
 * 
 * @author��liupei
 * @date��2011-6-16
 */
public class MvcUtilsTest {

	private WebActionContext context = null;

	@BeforeMethod
	public void beforeMethod() throws ConfigurationException, IOException, BeanInstantiationException, ClassNotFoundException {
		context = SothisFactory.initActionContext();
	}

	@AfterMethod
	public void afterMethod() {
		context.setContextMap(new HashMap<String, Object>());
	}

	/**
	 * ����MvcUtils.resolvePath()����
	 * 
	 * @param path
	 * @param actual
	 * @throws Exception
	 */
	@Test(dataProvider = "test")
	public void testResolvePath(String path, String expected) throws Exception {
		MockBeanFactory factory = new MockBeanFactory();
		MockActionInvocation invocation = new MockActionInvocation(context);

		Controller controller = new DefaultController(context.getConfiguration(), "", "test", TestController.class);

		invocation.setAction(controller.getAction("test"));

		Object controllerInstance = factory.getBean(controller.getControllerClass());
		invocation.setControllerInstance(controllerInstance);

		String actual = MvcUtils.resolvePath(path, invocation);

		Assert.assertEquals(actual, expected);
	}

	/**
	 * ���ݹ�������
	 * 
	 * @return
	 */
	@DataProvider(name = "test")
	public Object[][] interceptDataProvider() {
		List<String[]> paramList = new ArrayList<String[]>();

		// ��pathΪnull����Ϊ��������·��Ϊ"/test/test"
		paramList.add(new String[] { null, "/test/test" });

		// ��pathΪ���ַ�������Ϊ��������·��Ϊ"/test/test"
		paramList.add(new String[] { "", "/test/test" });

		// ��pathΪ����"/"��ͷ֮�����Ч�ַ�������Ϊ��������·��Ϊ"/test/abcd"
		paramList.add(new String[] { "abcd", "/test/abcd" });

		// ��pathΪ��"/"��ͷ���ַ�������Ϊ��������·��Ϊ"/abcd"
		paramList.add(new String[] { "/abcd", "/abcd" });

		// ��pathΪ"/"����Ϊ��������·��Ϊ"/"
		paramList.add(new String[] { "/", "/" });

		Object[][] params = new Object[paramList.size()][2];

		params = paramList.toArray(params);

		return params;
	}

	public static class TestController {
		public void testAction() {

		}
	}
}
