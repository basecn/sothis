package org.sothis.core.cache;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ����ͳ�ƽ����
 * 
 * @author velna
 * 
 */
public class CacheStat {

	private final static long PERIOD = 600000;

	private final String name;

	private final long startTime = System.currentTimeMillis();
	private final AtomicLong hits = new AtomicLong(0);
	private final AtomicLong puts = new AtomicLong(0);
	private final AtomicLong misses = new AtomicLong(0);
	private final AtomicLong requests = new AtomicLong(0);

	private long lStartTime = System.currentTimeMillis();
	private final AtomicLong lHits = new AtomicLong(0);
	private final AtomicLong lSets = new AtomicLong(0);
	private final AtomicLong lMisses = new AtomicLong(0);
	private final AtomicLong lRequests = new AtomicLong(0);

	/**
	 * ����һ������Ϊ{@code name}�Ļ���ͳ��
	 * 
	 * @param name
	 */
	public CacheStat(String name) {
		this.name = name;
	}

	/**
	 * ��¼һ��get����
	 * 
	 * @param hits
	 *            �����������
	 * @param misses
	 *            �����δ������
	 */
	public void requestGet(int hits, int misses) {
		checkPeriod();
		if (hits > 0) {
			this.hits.addAndGet(hits);
			this.lHits.addAndGet(hits);
		}
		if (misses > 0) {
			this.misses.addAndGet(misses);
			this.lMisses.addAndGet(misses);
		}
		this.requests.incrementAndGet();
		this.lRequests.incrementAndGet();
	}

	/**
	 * ��¼һ��put����
	 */
	public void requestPut() {
		checkPeriod();
		this.requests.incrementAndGet();
		this.lRequests.incrementAndGet();
		this.puts.incrementAndGet();
		this.lSets.incrementAndGet();
	}

	private void checkPeriod() {
		long now = System.currentTimeMillis();
		long time = now - this.lStartTime;
		if (time >= PERIOD) {
			lStartTime = now;
			this.lHits.set(0);
			this.lMisses.set(0);
			this.lSets.set(0);
			this.lRequests.set(0);
		}
	}

	/**
	 * �õ����������ɵ�ʱ�䡣ʵ��Ϊ��ʵ���ĳ�ʼ��ʱ��
	 * 
	 * @return
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * �õ����е��������������get��put��
	 * 
	 * @return
	 */
	public long getRequests() {
		return this.requests.get();
	}

	/**
	 * �õ����еĴ���
	 * 
	 * @return
	 */
	public long getHits() {
		return hits.get();
	}

	/**
	 * �õ�δ���еĴ���
	 * 
	 * @return
	 */
	public long getMisses() {
		return misses.get();
	}

	/**
	 * �õ�put�Ĵ���
	 * 
	 * @return
	 */
	public long getPuts() {
		return puts.get();
	}

	/**
	 * �õ����5���ӵ�ͳ�ƿ�ʼʱ��
	 * 
	 * @return
	 */
	public long getLStartTime() {
		return lStartTime;
	}

	/**
	 * �õ����5���ӵ�����������
	 * 
	 * @return
	 */
	public long getLRequests() {
		return this.lRequests.get();
	}

	/**
	 * �õ����5���ӵ����д���
	 * 
	 * @return
	 */
	public long getLHits() {
		return lHits.get();
	}

	/**
	 * �õ����5���ӵ�δ���д���
	 * 
	 * @return
	 */
	public long getLMisses() {
		return lMisses.get();
	}

	/**
	 * �õ����5���ӵ�put����
	 * 
	 * @return
	 */
	public long getLPuts() {
		return lSets.get();
	}

	/**
	 * �õ�ÿ�����д���
	 * 
	 * @return
	 */
	public double getHitRate() {
		return this.hits.get() * 1000.0 / (System.currentTimeMillis() - this.startTime);
	}

	/**
	 * �õ�ÿ��δ���д���
	 * 
	 * @return
	 */
	public double getMissRate() {
		return this.misses.get() * 1000.0 / (System.currentTimeMillis() - this.startTime);
	}

	/**
	 * �õ�ÿ��put����
	 * 
	 * @return
	 */
	public double getPutRate() {
		return this.puts.get() * 1000.0 / (System.currentTimeMillis() - this.startTime);
	}

	/**
	 * �õ�ÿ���������
	 * 
	 * @return
	 */
	public double getRequestRate() {
		return this.requests.get() * 1000.0 / (System.currentTimeMillis() - this.startTime);
	}

	/**
	 * �õ����5����ÿ�����д���
	 * 
	 * @return
	 */
	public double getLHitRate() {
		return this.lHits.get() * 1000.0 / (System.currentTimeMillis() - this.lStartTime);
	}

	/**
	 * �õ����5����ÿ��δ���д���
	 * 
	 * @return
	 */
	public double getLMissRate() {
		return this.lMisses.get() * 1000.0 / (System.currentTimeMillis() - this.lStartTime);
	}

	/**
	 * �õ����5����ÿ��put����
	 * 
	 * @return
	 */
	public double getLPutRate() {
		return this.lSets.get() * 1000.0 / (System.currentTimeMillis() - this.lStartTime);
	}

	/**
	 * �õ����5����ÿ��������
	 * 
	 * @return
	 */
	public double getLRequestRate() {
		return this.lRequests.get() * 1000.0 / (System.currentTimeMillis() - this.lStartTime);
	}

	/**
	 * ����ͳ�Ƶ�����
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

}
