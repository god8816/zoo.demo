package com.concurrent.threadpoolexecutor;

/**
 * retry 语法研究
 * @描述：retry作用相当于goto，当循环满足某个条件直接跳出for循环，从头开始执行。
 * @功能： 可以用retry for实现while迭代功能
 * @author dzc
 * */
public class RetryTest {

	public static void main(String[] args) {
		test(2);

	}
	
	public static void test(int i) {
		retry:
        for (;;) {
            System.out.println("1");
            for (;;) {
                System.out.println("2");
                if (i == 1) {
                    return;
                } else if (i == 2) {
                    break retry;
                } else if (i == 3) {
                    continue retry;
                }
            }
        }
        System.out.println("end");
    }

}
