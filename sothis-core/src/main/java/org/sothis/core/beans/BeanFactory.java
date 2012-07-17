package org.sothis.core.beans;

/**
 * Bean����<br>
 * 
 * @author velna
 * 
 */
public interface BeanFactory {
	/**
	 * ����bean��class���õ�bean<br>
	 * bean����˳�����£�<br>
	 * 1.����beanClass��simple name����bean����{@link Class#getSimpleName()}��simple
	 * name������ĸ����ת����Сд<br>
	 * 2.����������class name����bean����{@link Class#getName()} <br>
	 * 3.����{@link Bean}ע�������ע�����beanClass�����û��{@link Bean}ע�⣬��ʹ��{@link Bean}
	 * �е�Ĭ��ֵ
	 * 
	 * @param <T>
	 * @param beanClass
	 * @return
	 * @throws BeanInstantiationException
	 *             �����bean�������̷����κ��쳣
	 */
	<T> T getBean(Class<T> beanClass) throws BeanInstantiationException;

	/**
	 * �õ�nameΪbeanName��bean���������beanע�ᶯ��
	 * 
	 * @param <T>
	 * @param beanName
	 * @return ���δ�ҵ��򷵻�null
	 * @throws BeanInstantiationException
	 *             �����bean�������̷����κ��쳣
	 */
	<T> T getBean(String beanName) throws BeanInstantiationException;
}
