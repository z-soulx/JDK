package base;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
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

}
