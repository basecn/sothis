package org.sothis.dal;

import java.io.Serializable;
import java.util.List;

import org.sothis.core.util.Pager;
import org.sothis.dal.query.Chain;
import org.sothis.dal.query.Cnd;

/**
 * ���ݿ���ʽӿڡ����������ݿ���ʵĳ��÷�����
 * 
 * @author velna
 * 
 * @param <E>
 * @param <K>
 */
public interface Dao<E extends Entity, K extends Serializable> {

	/**
	 * ���ر�DAO��Ӧ�����ݿ�ʵ����
	 * 
	 * @return
	 */
	Class<E> getEntityClass();

	/**
	 * ����������ѯ���ݿ⣬������һ���б�<br>
	 * {@code dao.find(Cnd.make("age", 22).asc("id"), Pager.make(0, 10), Chain.make("username").add("gender"));}
	 * <br>
	 * �൱��select username, gender from mytable where age=22 order by id asc
	 * limit 0, 10
	 * 
	 * @param cnd
	 *            ��ѯ����
	 * @param pager
	 *            ��ҳ�����Ϊnull�򷵻��������ݼ�¼
	 * @param chain
	 *            ��Ҫ���ص��ֶμ������Ϊnull���ѯ�����ֶ�
	 * @return
	 */
	List<E> find(Cnd cnd, Pager pager, Chain chain);

	/**
	 * �൱��{@code find(cnd, pager, null)}
	 * 
	 * @param cnd
	 * @param pager
	 * @return
	 */
	List<E> find(Cnd cnd, Pager pager);

	/**
	 * �൱��{@code find(cnd, null, null)}
	 * 
	 * @param cnd
	 * @return
	 */
	List<E> find(Cnd cnd);

	/**
	 * �൱��{@code find(cnd, Pager.make(0,1), chain)}
	 * 
	 * @param cnd
	 * @param chain
	 * @return
	 */
	E findOne(Cnd cnd, Chain chain);

	/**
	 * �൱��{@code find(cnd, Pager.make(0,1), null)}
	 * 
	 * @param cnd
	 * @return
	 */
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

	/**
	 * ���ݲ�ѯ����{@code cnd}�������ݿ⡣<br>
	 * {@code dao.update(Cnd.make("age", 24), Chain.make("age", 23));}<br>
	 * �൱�ڣ�update mytable set age=23 where age=24
	 * 
	 * @param cnd
	 * @param chain
	 * @return ��Ӱ��ļ�¼��
	 */
	int update(Cnd cnd, Chain chain);

	/**
	 * ����id��������<br>
	 * 
	 * @param id
	 * @param chain
	 * @return
	 */
	int updateById(K id, Chain chain);

	/**
	 * ����������ʵ�����е������ֶθ��������ݿ��С�
	 * 
	 * @param entity
	 * @return ��Ӱ��ļ�¼��
	 */
	E update(E entity);

	/**
	 * ���ݲ�ѯ����ɾ����¼��
	 * 
	 * @param cnd
	 * @return ��Ӱ��ļ�¼��
	 */
	int delete(Cnd cnd);

	/**
	 * ����idɾ��һ������
	 * 
	 * @param id
	 * @return ��Ӱ��ļ�¼��
	 */
	int deleteById(K id);

	/**
	 * ����id�б�ɾ��һ������
	 * 
	 * @param idList
	 * @return ��Ӱ��ļ�¼��
	 */
	int deleteByIds(List<K> idList);

	/**
	 * ����һ����¼��
	 * 
	 * @param entity
	 * @return
	 */
	E insert(E entity);

	/**
	 * �õ����ݱ�����м�¼������
	 * 
	 * @return
	 */
	int count();

	/**
	 * ���ݲ�ѯ�����õ�����������ļ�¼����
	 * 
	 * @param cnd
	 * @return
	 */
	int count(Cnd cnd);
}
