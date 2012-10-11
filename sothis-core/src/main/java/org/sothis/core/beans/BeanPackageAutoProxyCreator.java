package org.sothis.core.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * ����package�����������Զ�����ͬʱҲ����ͨ������������������Զ�����Ĵ�����
 * 
 * @author velna
 * 
 */
public class BeanPackageAutoProxyCreator extends AbstractAutoProxyCreator {

	private static final long serialVersionUID = -3344529761123559113L;

	private List<String> beanPackages;

	private Class<?> assignableClass;

	/**
	 * ���������Զ���������İ���������������ȫƥ�䡣
	 * 
	 * @param beanPackages
	 */
	public void setBeanPackages(String[] beanPackages) {
		Assert.notEmpty(beanPackages, "'beanNames' must not be empty");
		this.beanPackages = new ArrayList<String>(beanPackages.length);
		for (String mappedName : beanPackages) {
			this.beanPackages.add(StringUtils.trimWhitespace(mappedName));
		}
	}

	/**
	 * �����������ƴ����������͡�ֻ��{@code assignableClass}��ָ�����༰��������Զ���������
	 * 
	 * @param assignableClass
	 */
	public void setAssignableClass(Class<?> assignableClass) {
		this.assignableClass = assignableClass;
	}

	@Override
	protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource targetSource) {
		if (this.beanPackages != null) {
			for (String mappedPackage : this.beanPackages) {
				if (isMatch(beanClass, mappedPackage)) {
					return PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
				}
			}
		}
		return DO_NOT_PROXY;
	}

	protected boolean isMatch(Class<?> beanClass, String mappedPackage) {
		if (null != assignableClass && !assignableClass.isAssignableFrom(beanClass)) {
			return false;
		}
		Package pkg = beanClass.getPackage();
		if (null != pkg) {
			return mappedPackage.equals(pkg.getName());
		}
		return false;
	}

}
