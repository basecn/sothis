package org.sothis.core.beans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����������ע�⣬����������bean����
 * 
 * @author velna
 * 
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
	/**
	 * bean������Ĭ���ǵ���
	 * 
	 * @return
	 */
	Scope scope() default Scope.SINGLETON;

	/**
	 * bean�Զ�װ��ģʽ��Ĭ���Ǹ��������Զ�װ��(BY_NAME)
	 * 
	 * @return
	 */
	Autowire autowire() default Autowire.BY_NAME;

	/**
	 * beanʵ������ĳ�ʼ������������
	 * 
	 * @return
	 */
	String initMethod() default "";
}
