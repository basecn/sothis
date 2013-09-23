package org.sothis.dal;

import java.io.Serializable;
import java.util.List;

import org.sothis.core.util.Cursor;
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
	 * @param fields
	 *            ��Ҫ���ص��ֶμ������Ϊnull���ѯ�����ֶ�
	 * @return
	 */
	List<E> find(Cnd cnd, Pager pager, Chain fields);

	/**
	 * �൱��{@code find(cnd, pager, fields)}<br>
	 * {@code pager.setTotalRows(count(cnd))}
	 * 
	 * @param cnd
	 * @param pager
	 * @param fields
	 * @return
	 */
	List<E> findAndCount(Cnd cnd, Pager pager, Chain fields);

	/**
	 * �൱��{@code find(cnd, pager, null)}<br>
	 * {@code pager.setTotalRows(count(cnd))}
	 * 
	 * @param cnd
	 * @param pager
	 * @return
	 */
	List<E> findAndCount(Cnd cnd, Pager pager);

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
	 * �Ե�����ʽ��ѯ��
	 * 
	 * @param cnd
	 * @param fields
	 * @return
	 */
	Cursor<E> cursor(Cnd cnd, Chain fields);

	/**
	 * �൱��{@code find(cnd, Pager.make(0,1), fields)}
	 * 
	 * @param cnd
	 * @param fields
	 * @return
	 */
	E findOne(Cnd cnd, Chain fields);

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
	 * @param update
	 * @return ��Ӱ��ļ�¼��
	 */
	int update(Cnd cnd, Chain update);

	/**
	 * ����id��������<br>
	 * 
	 * @param id
	 * @param update
	 * @return
	 */
	int updateById(K id, Chain update);

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
