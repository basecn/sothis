package org.sothis.dal.query;

import java.util.Iterator;

/**
 * �ṩ���ķ���������{@code name}��{@code name value}������
 * 
 * @author velna
 * 
 */
public class Chain implements Iterable<Chain> {

	private String name;
	private Object value;
	private Chain next;
	private ChainParent parent;

	protected Chain(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	protected Chain() {
		this.parent = new ChainParent(this);
	}

	/**
	 * ����һ����ֵ������
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static Chain make(String name, Object value) {
		if (null == name) {
			throw new IllegalArgumentException("name can not be null");
		}
		Chain chain = new Chain(name, value);
		ChainParent parent = new ChainParent(chain);
		parent.size++;
		chain.parent = parent;
		return chain;
	}

	/**
	 * ����һ��ֻ����������
	 * 
	 * @param name
	 * @return
	 */
	public static Chain make(String name) {
		return make(name, null);
	}

	/**
	 * ����һ���յ�����
	 * 
	 * @return
	 */
	public static Chain make() {
		return new Chain();
	}

	/**
	 * ���һ����ֵ��
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public synchronized Chain add(String name, Object value) {
		if (this.name == null) {
			this.name = name;
			this.value = value;
			this.parent.size++;
			return this;
		} else {
			Chain chain = new Chain(name, value);
			chain.parent = this.parent;
			chain.next = this.next;
			this.next = chain;
			this.parent.size++;
			return chain;
		}
	}

	/**
	 * ���һ����
	 * 
	 * @param name
	 * @return
	 */
	public synchronized Chain add(String name) {
		return add(name, null);
	}

	/**
	 * �õ��������һ���ڵ�
	 * 
	 * @return
	 */
	public Chain next() {
		return this.next;
	}

	/**
	 * �õ���������нڵ�����
	 * 
	 * @return
	 */
	public int size() {
		return this.parent.size;
	}

	/**
	 * �õ����ڵ����
	 * 
	 * @return
	 */
	public String name() {
		return name;
	}

	/**
	 * �õ����ڵ��ֵ
	 * 
	 * @return
	 */
	public Object value() {
		return value;
	}

	@Override
	public Iterator<Chain> iterator() {
		return new ChainIterator(this.parent.head);
	}

	private static class ChainParent {
		final Chain head;
		int size;

		public ChainParent(Chain head) {
			this.head = head;
		}
	}

	private static class ChainIterator implements Iterator<Chain> {
		private Chain head;

		public ChainIterator(Chain head) {
			this.head = head;
		}

		@Override
		public boolean hasNext() {
			return this.head != null;
		}

		@Override
		public Chain next() {
			Chain ret = head;
			head = head.next;
			return ret;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove is not allowed");
		}

	}
}
