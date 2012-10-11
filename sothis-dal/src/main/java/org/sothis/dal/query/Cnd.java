package org.sothis.dal.query;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ��ѯ�����ࡣͨ��������Լ������ɲ�ѯ������
 * 
 * @author velna
 * 
 */
public class Cnd implements OrderBy {
	private final Object left;
	private final Object op;
	private final Object right;
	private boolean not;
	private final List<Sort> sorts = new LinkedList<Sort>();

	private Cnd() {
		this.left = null;
		this.op = null;
		this.right = null;
	}

	private Cnd(Object left, Object op, Object right) {
		if (null == op) {
			throw new IllegalArgumentException("op can not be null");
		}
		this.left = left;
		this.op = op;
		this.right = right;
	}

	/**
	 * ����һ���ɼ򵥱��ʽ��ʼ�Ĳ�ѯ����
	 * 
	 * @param field
	 *            �ֶ���
	 * @param op
	 *            ������
	 * @param value
	 *            �ֶ�ֵ
	 * @return
	 */
	public static Cnd make(String field, Object op, Object value) {
		return new Cnd(field, op, value);
	}

	/**
	 * �൱��Cnd.make(field, Op.EQ, value);
	 * 
	 * @param field
	 * @param value
	 * @return
	 */
	public static Cnd make(String field, Object value) {
		return new Cnd(field, Op.EQ, value);
	}

	/**
	 * ��ǰ�������һ���򵥱��ʽ������and�߼��������������
	 * 
	 * @param field
	 * @param op
	 * @param value
	 * @return
	 */
	public Cnd and(String field, Object op, Object value) {
		Cnd cnd = new Cnd(field, op, value);
		return and(this, cnd);
	}

	/**
	 * �൱��cnd.and(field, Op.EQ, value);
	 * 
	 * @param field
	 * @param value
	 * @return
	 */
	public Cnd and(String field, Object value) {
		Cnd cnd = new Cnd(field, Op.EQ, value);
		return and(this, cnd);
	}

	/**
	 * ��ǰ�������һ������������and�߼��������������
	 * 
	 * @param cnd
	 * @return
	 */
	public Cnd and(Cnd cnd) {
		return and(this, cnd);
	}

	/**
	 * ��ǰ�������һ������������or�߼��������������
	 * 
	 * @param cnd
	 * @return
	 */
	public Cnd or(Cnd cnd) {
		return or(this, cnd);
	}

	/**
	 * ��ǰ�������һ���򵥱��ʽ������or�߼��������������
	 * 
	 * @param field
	 * @param op
	 * @param value
	 * @return
	 */
	public Cnd or(String field, Object op, Object value) {
		Cnd cnd = new Cnd(field, op, value);
		return or(this, cnd);
	}

	/**
	 * �൱��cnd.or(field, Op.EQ, value);
	 * 
	 * @param field
	 * @param value
	 * @return
	 */
	public Cnd or(String field, Object value) {
		Cnd cnd = new Cnd(field, Op.EQ, value);
		return or(this, cnd);
	}

	/**
	 * ����һ��������������ɵ�������������������and�߼��������������
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static Cnd and(Cnd left, Cnd right) {
		return new Cnd(left, Logic.AND, right);
	}

	/**
	 * ����һ��������������ɵ�������������������or�߼��������������
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static Cnd or(Cnd left, Cnd right) {
		return new Cnd(left, Logic.OR, right);
	}

	/**
	 * �Ե�ǰ��������ȡ���߼�����
	 * 
	 * @return
	 */
	public Cnd not() {
		this.not = true;
		return this;
	}

	/**
	 * ����һ��ֻ�����������
	 * 
	 * @param field
	 * @param asc
	 * @return
	 */
	public static Cnd orderBy(String field, boolean asc) {
		if (asc) {
			return new Cnd().asc(field);
		} else {
			return new Cnd().desc(field);
		}
	}

	@Override
	public Cnd asc(String field) {
		this.sorts.add(new Sort(field, true));
		return this;
	}

	@Override
	public Cnd desc(String field) {
		this.sorts.add(new Sort(field, false));
		return this;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		if (not) {
			ret.append('!');
		}
		if (not || op instanceof Logic) {
			ret.append("(");
		}
		ret.append(left).append(' ').append(op).append(' ').append(right);
		if (not || op instanceof Logic) {
			ret.append(")");
		}
		return ret.toString();
	}

	/**
	 * �õ��������������
	 * 
	 * @return
	 */
	public Object getLeft() {
		return left;
	}

	/**
	 * �õ������Ĳ�����
	 * 
	 * @return
	 */
	public Object getOp() {
		return op;
	}

	/**
	 * �õ��������������
	 * 
	 * @return
	 */
	public Object getRight() {
		return right;
	}

	/**
	 * �õ���ǰ�����Ƿ���ȡ��
	 * 
	 * @return
	 */
	public boolean isNot() {
		return not;
	}

	/**
	 * �õ���ǰ�����Ƿ�ֻ������
	 * 
	 * @return
	 */
	public boolean isOrderByOnly() {
		return op == null;
	}

	@Override
	public List<Sort> getSorts() {
		return Collections.unmodifiableList(sorts);
	}

}
