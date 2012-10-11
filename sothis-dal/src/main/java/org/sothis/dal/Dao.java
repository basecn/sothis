package org.sothis.dal;

import java.io.Serializable;
import java.util.List;

import org.sothis.core.util.Pager;
import org.sothis.dal.query.Chain;
import org.sothis.dal.query.Cnd;

public interface Dao<E extends Entity, K extends Serializable> {
	public final static String EXECUTE_COUNTER_KEY = "org.sothis.dal.EntityDao.EXECUTE_COUNTER_KEY";

	Class<E> getEntityClass();

	List<E> find(Cnd cnd, Pager pager, Chain chain);

	List<E> find(Cnd cnd, Pager pager);

	List<E> find(Cnd cnd);

	E findOne(Cnd cnd, Chain chain);

	E findOne(Cnd cnd);

	/**
	 * ����id���Ҷ���
	 * 
	 * @param id
	 * @return
	 */
	E findById(K id);

	/**
	 * ����id�б�������ж��󣬷��ص��б���ݴ����idList����
	 * 
	 * @param idList
	 * @return
	 */
	List<E> findByIds(List<K> idList);

	int update(Cnd cnd, Chain chain);

	/**
	 * ����id��������
	 * 
	 * @param data
	 *            ��Ҫ���µ��ֶ������ֶ�ֵ��keyΪ�ֶ�����valueΪ�ֶ�ֵ
	 * @param id
	 */
	int updateById(K id, Chain chain);

	E update(E entity);

	int delete(Cnd cnd);

	/**
	 * ����idɾ��һ������
	 * 
	 * @param id
	 */
	int deleteById(K id);

	/**
	 * ����id�б�ɾ��һ������
	 * 
	 * @param idList
	 */
	int deleteByIds(List<K> idList);

	E insert(E entity);

	int count();

	int count(Cnd cnd);
}
