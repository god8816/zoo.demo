package com.concurrent.threadlocal;

/**
 * 神奇数据测试
 * */
public class NumTest {
	
	private static final int HASH_INCREMENT = 0x61c88647;

	public static void main(String[] args) {
		//new NumTest().test();
		hashCode(16); //初始化16
        hashCode(32); //后续2倍扩容
        hashCode(64);
	}
	
	
	public  void test() {
		long l1 = (long) ((1L << 32) * (Math.sqrt(5) - 1)/2);
        System.out.println("as 32 bit unsigned: " + l1);
        int i1 = (int) l1;
        System.out.println("as 32 bit signed:   " + i1);
        System.out.println("MAGIC = " + 0x61c88647);
	}
	
	
    private static void hashCode(Integer length){
        int hashCode = 0;
        for(int i=0; i< length; i++){
            hashCode = i * HASH_INCREMENT+HASH_INCREMENT;//每次递增HASH_INCREMENT
            System.out.print(hashCode & (length-1));
            System.out.print(" ");
        }
        System.out.println();
    }
}
