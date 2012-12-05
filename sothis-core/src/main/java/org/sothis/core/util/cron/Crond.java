package org.sothis.core.util.cron;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.sothis.core.util.BlockingThreadPoolExecutor;

/**
 * ����ִ��cron����ĺ�̨����
 * 
 * @author velna
 * 
 */
public class Crond {

	private final Executor executor;
	private final Map<Object, CronJob> jobs = new ConcurrentHashMap<Object, CronJob>();
	private final List<Object> nextKeys = new LinkedList<Object>();
	private Date nextDate = new Date();
	private Thread crondThread = null;
	private final Lock lock = new ReentrantLock();
	private final Condition nextUpdated = lock.newCondition();

	/**
	 * ����cron����
	 * 
	 * @param executor
	 *            ����ִ�������Executor
	 */
	public Crond(Executor executor) {
		this.executor = executor;
	}

	/**
	 * ��ͬ��
	 * {@code Crond(new BlockingThreadPoolExecutor("CrondWroker", 1, 10, 10000, 60000))}
	 */
	public Crond() {
		this(new BlockingThreadPoolExecutor("CrondWroker", 1, 10, 10000, 60000));
	}

	/**
	 * ����cron���񡣶���������cron���񲻻�����κ�Ч��
	 */
	public void start() {
		if (null == crondThread || !crondThread.isAlive()) {
			crondThread = new Thread(new CrondRunnable(), "Crond");
			crondThread.setDaemon(true);
			crondThread.start();
		}
	}

	/**
	 * ֹͣcron���񡣶���δ��������ֹͣ��cron���񲻻�����κ�Ч��
	 */
	public void stop() {
		if (null != crondThread && crondThread.isAlive()) {
			crondThread.interrupt();
		}
	}

	/**
	 * ��cron�������һ������
	 * 
	 * @param key
	 *            �����Ψһ��ʶ��set��ͬ��key�Ḳ��ǰһ��
	 * @param cron
	 *            �����cron���ʽ
	 * @param runnable
	 *            ����Runnable
	 */
	public void set(Object key, Cron cron, Runnable runnable) {
		Date next = cron.next(this.nextDate);
		lock.lock();
		try {
			jobs.put(key, new CronJob(cron, runnable));
			if (nextKeys.isEmpty()) {
				nextKeys.add(key);
				nextDate = next;
				nextUpdated.signalAll();
			} else if (next.before(this.nextDate)) {
				nextKeys.clear();
				nextKeys.add(key);
				nextDate = next;
				nextUpdated.signalAll();
			} else if (next.equals(this.nextDate)) {
				nextKeys.add(key);
				nextUpdated.signalAll();
			}
		} finally {
			lock.unlock();
		}
	}

	private void next() {
		lock.lock();
		try {
			nextKeys.clear();
			Date next = null;
			for (Map.Entry<Object, CronJob> entry : jobs.entrySet()) {
				Date jobNext = entry.getValue().getCron().next(this.nextDate);
				if (null == next || jobNext.equals(next)) {
					nextKeys.add(entry.getKey());
					next = jobNext;
				} else if (jobNext.before(next)) {
					nextKeys.clear();
					nextKeys.add(entry.getKey());
					next = jobNext;
				}
			}
			if (null != next) {
				this.nextDate = next;
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * ��cron������ɾ��һ������
	 * 
	 * @param key
	 */
	public void remove(Object key) {
		lock.lock();
		try {
			jobs.remove(key);
			next();
		} finally {
			lock.unlock();
		}
	}

	private class CrondRunnable implements Runnable {

		@Override
		public void run() {
			while (true) {
				lock.lock();
				try {
					if (nextKeys.isEmpty()) {
						nextUpdated.await();
					}
					long offset;
					while ((offset = System.currentTimeMillis() - nextDate.getTime()) >= 1000) {
						next();
					}
					while (offset < 1000 && offset >= 0) {
						for (Object key : nextKeys) {
							executor.execute(jobs.get(key).getJob());
						}
						next();
						offset = System.currentTimeMillis() - nextDate.getTime();
					}
					nextUpdated.await(nextDate.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					break;
				} finally {
					lock.unlock();
				}
			}
		}

	}

	private static class CronJob {
		private final Cron cron;
		private final Runnable job;

		public CronJob(Cron cron, Runnable job) {
			this.cron = cron;
			this.job = job;
		}

		public Cron getCron() {
			return cron;
		}

		public Runnable getJob() {
			return job;
		}

	}

}
