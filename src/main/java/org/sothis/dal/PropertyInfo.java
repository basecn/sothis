package org.sothis.dal;

import java.beans.PropertyDescriptor;

import org.sothis.dal.annotation.Column;

/**
 * ʵ�����ֶ�������Ϣ
 * 
 * @author velna
 * 
 */
public class PropertyInfo {

	private final PropertyDescriptor propertyDescriptor;
	private final Column column;
	private final Class<?> clazz;
	private final boolean id;

	public PropertyInfo(PropertyDescriptor propertyDescriptor, Column column,
			Class<?> clazz, boolean id) {
		if (!column.name().toLowerCase().equals(column.name())) {
			throw new IllegalArgumentException("name of column ["
					+ propertyDescriptor.getName()
					+ "] must be lower cased of class " + clazz.getName()
					+ ", current is [" + column.name() + "]");
		}
		this.propertyDescriptor = propertyDescriptor;
		this.column = column;
		this.clazz = clazz;
		this.id = id;
	}

	/**
	 * �õ��ֶε�������Ϣ
	 * 
	 * @return
	 */
	public PropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	/**
	 * �õ�Columnע��
	 * 
	 * @return
	 */
	public Column getColumn() {
		return column;
	}

	/**
	 * �Ƿ�Ϊid�ֶ�
	 * 
	 * @return
	 */
	public boolean isID() {
		return id;
	}

	/**
	 * �õ�������ʵ����
	 * 
	 * @return
	 */
	public Class<?> getClazz() {
		return clazz;
	}

}
