package org.sothis.dal.query;

/**
 * �����ֶ�
 * 
 * @author velna
 * 
 */
public class Sort {
	private final String field;
	private final boolean asc;

	public Sort(String field, boolean asc) {
		this.field = field;
		this.asc = asc;
	}

	/**
	 * ������ֶ���
	 * 
	 * @return
	 */
	public String getField() {
		return field;
	}

	/**
	 * 
	 * @return ���򷵻�true�����򷵻�false
	 */
	public boolean isAsc() {
		return asc;
	}

}
