package com.concurrent.threadlocal;


/**
 *  withInitial 创建线程局部安全变量
 * */
public class ThreadLocalWithInitialTest {
	
	
	public static void main(String[] args) {
		//safeDeposit();
        notSafeDeposit(); 
	}
	

    /**
     * 线程安全的存款
     */
    private static void safeDeposit() {
        SafeBank bank = new ThreadLocalWithInitialTest().new SafeBank();
        Thread thread1 = new Thread(() -> bank.deposit(200), "张成瑶");
        Thread thread2 = new Thread(() -> bank.deposit(200), "马云");
        Thread thread3 = new Thread(() -> bank.deposit(500), "马化腾");
        thread1.start();
        thread2.start();
        thread3.start();
    }
 
    /**
     * 非线程安全的存款
     */
    private static void notSafeDeposit() {
        NotSafeBank bank = new ThreadLocalWithInitialTest().new NotSafeBank();
        Thread thread1 = new Thread(() -> bank.deposit(200), "张成瑶");
        Thread thread2 = new Thread(() -> bank.deposit(200), "马云");
        Thread thread3 = new Thread(() -> bank.deposit(500), "马化腾");
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    /**
     * 非线程安全的存款
     */
    class NotSafeBank{
     	/**
         * 当前余额不受保护
         */
        private int balance = 1000;
     
        /**
         * 存款
         *
         * @param money 存款金额
         */
        public void deposit(int money) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " -> 当前账户余额为：" + this.balance);
            this.balance += money;
            System.out.println(threadName + " -> 存入 " + money + " 后，当前账户余额为：" + this.balance);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 


    
    
	/**
	* 线程安全的银行
	*/
	class SafeBank {
	
			/**
			 * 当前余额受保护
			 */
			private ThreadLocal<Integer> balance = ThreadLocal.withInitial(() -> 1000);
			
			/**
			 * 存款
			 *
			 * @param money 存款金额
			 */
			public void deposit(int money) {
			    String threadName = Thread.currentThread().getName();
			    System.out.println(threadName + " -> 当前账户余额为：" + this.balance.get());
			    this.balance.set(this.balance.get() + money);
			    System.out.println(threadName + " -> 存入 " + money + " 后，当前账户余额为：" + this.balance.get());
			    try {
			        Thread.sleep(1000);
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			}
	 }
}
