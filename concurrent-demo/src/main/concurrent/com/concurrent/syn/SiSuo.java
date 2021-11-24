package com.concurrent.syn;

/**
 * 死锁场景
 * */
public class SiSuo {
	
	private static final Object left = new Object();
    private static final Object right = new Object();
    
    public static void leftRight() throws Exception
    {
        synchronized (left)
        {
            Thread.sleep(20000);
            synchronized (right)
            {
                System.out.println("leftRight end!");
            }
        }
    }
    
    public static void rightLeft() throws Exception
    {
        synchronized (right)
        {
            Thread.sleep(20000);
            synchronized (left)
            {
                System.out.println("rightLeft end!");
            }
        }
    }

	public static void main(String[] args) throws Exception {
		leftRight();
		rightLeft();
	}

}
