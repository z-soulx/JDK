package condition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.*;

public class Demo {
	
	private final Lock lock = new ReentrantLock();
	private final ReadWriteLock lock2 = new ReentrantReadWriteLock();
	private final Condition condition = lock.newCondition();
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
	private class T implements Runnable {
		@Override
		public void run() {
			try {
				lock.lock();
//				lock2.lockInterruptibly();
				try {
					System.out.println("开始等待");
					condition.await();
					condition.awaitNanos(1000);
					System.out.println("唤醒");
//					while (true){
//						Thread.sleep(1);
//					}
//					System.out.println(Thread.currentThread().isInterrupted());
				} finally {
					lock.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void signal() throws InterruptedException {
		lock.lock();
		try {
			System.out.println("获得锁");
			condition.signalAll();
			condition.signal();
			while (true){
						Thread.sleep(1);
					}
//			System.out.println("未解锁");
		} finally {
			lock.unlock();
		}
	}

	public void testCondition(){
		try {
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Demo demo = new Demo();
		new Thread(demo.new T()).start();
		Thread.sleep(2000);
		demo.signal();
//		demo.testCondition();
	}

}
