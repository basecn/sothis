package org.sothis.core.util.cron;

import java.util.Calendar;

public interface Field {

	/**
	 * �жϱ��ֶκ�һ�������Ƿ�ƥ��
	 * 
	 * @param calendar
	 * @return trueΪƥ�䣬falseΪ��ƥ��
	 */
	boolean matches(Calendar calendar);

	/**
	 * ���ֶ��Ƿ�δ����
	 * 
	 * @return
	 */
	boolean isBlank();

	/**
	 * ���ֶεı��ʽ
	 * 
	 * @return
	 */
	String getExpression();
}
