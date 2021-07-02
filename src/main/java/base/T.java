package base;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.junit.Test;

/**
 * @program: JDK
 * @description:
 * @author: soulx
 * @create: 2021-06-23 10:54
 **/
public class T {

	public static void main(String[] args) {
		ConcurrentHashMap c = new ConcurrentHashMap();

		for (int i = 0; i < 14; i++) {
			c.put(i,"11");
		}

		System.out.println();
	}


	static final int resizeStamp(int n) {
		int zeroCount = Integer.numberOfLeadingZeros(n);
		return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
	}
	private static int RESIZE_STAMP_BITS = 16;

	private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;
	@Test
	public  void testConcurrentHashMap() {
		// <0 时溢出，结束
		for(int n=1;n>0;n*=2){
			int rs = resizeStamp(n);
			int ans = (rs << RESIZE_STAMP_SHIFT) + 2;
			System.out.println("n:"+n+"    rs:"+rs+"  an:"+ans);
			System.out.println(" rs:"+Integer.toBinaryString(rs));
			System.out.println(" an:"+Integer.toBinaryString(ans));
		}
	}

	@Test
	public void concurrentQueue(){
		Integer s =1;

		System.out.println(s==(s=2));
		System.out.println(s);
		ConcurrentLinkedQueue<Integer> q =new ConcurrentLinkedQueue();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(3);

	}
	private String t(){
		System.out.println("t");
		return "";
	}
	private String t2(String s){
		System.out.println("t2");
		return  "11";
	}
	@Test
	public void Queue(){
		new ArrayBlockingQueue(1);
	}

}
