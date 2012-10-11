package org.sothis.dal.query;

import java.util.List;

/**
 * ��������
 * 
 * @author velna
 * 
 */
public interface OrderBy {

	/**
	 * ���һ����{@code field}�������������
	 * 
	 * @param field
	 * @return
	 */
	OrderBy asc(String field);

	/**
	 * ���һ����{@code field}�������������
	 * 
	 * @param field
	 * @return
	 */
	OrderBy desc(String field);

	/**
	 * �õ����е���������
	 * 
	 * @return
	 */
	List<Sort> getSorts();

}
