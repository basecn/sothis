package org.sothis.core.util.cron;

import java.util.Calendar;

/**
 * �ֶ��ڲ���ƥ������ƥ���Զ��ŷָ��ĵ�����
 * 
 * @author velna
 * 
 */
public interface Matcher {
	/**
	 * �ж�������Ƿ��ĳһʱ���ĳ���ֶ���ƥ��
	 * 
	 * @param calendar
	 * @param field
	 *            Calendar���ֶ�
	 * @return
	 */
	boolean matches(Calendar calendar, int field);
}
