类解释：Condition
1: Condition.await()                          当前线程进入等待状态直到被通知(signal)或被中断
2: Condition.awaitUninterruptibly()           不响应中断等待，直到被通知(signal)
3: Condition.awaitNanos(long nanosTimeout)    等待指定时长直到被通知或中断或超时
4: Condition.await(long time, TimeUnit unit)  等待指定时长直到被通知或中断或超时
5: Condition.awaitUntil(Date deadline)        等待指定时长直到被通知或中断或超时
6: Condition.signal()                         唤醒一个等待在Condition上的线程，该线程从等待方法返回前必须获得与Condition相关联的锁
7: Condition.signalAll()                      唤醒所有等待在Condition上的线程，该线程从等待方法返回前必须获得与Condition相关联的锁


方法解释：
  创建：
    Condition 通过实现Lock.newCondition()接口的类初始化的。
    例如：
     ReentrantLock.newCondition();
     ReentrantReadWriteLock.newCondition();
  
  await()方法：
    public final void await() throws InterruptedException {
            //判断线程释放是中断状态
            if (Thread.interrupted())
                throw new InterruptedException();
            Node node = addConditionWaiter();
            int savedState = fullyRelease(node);
            int interruptMode = 0;
            while (!isOnSyncQueue(node)) {
                //此处阻塞线程
                LockSupport.park(this);
                if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                    break;
            }
            if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            if (node.nextWaiter != null) // clean up if cancelled
                unlinkCancelledWaiters();
            if (interruptMode != 0)
                reportInterruptAfterWait(interruptMode);
        }
   
   signal()方法：     
	   public final void signal() {
	            if (!isHeldExclusively())
	                throw new IllegalMonitorStateException();
	            Node first = firstWaiter;
	            if (first != null)
	                //
	                doSignal(first);
	   }     
	   
	   private void doSignal(Node first) {
	            do {
	                if ( (firstWaiter = first.nextWaiter) == null)
	                    lastWaiter = null;
	                first.nextWaiter = null;
	            } while (!transferForSignal(first) &&
	                     (first = firstWaiter) != null);
	   }
	   
	   final boolean transferForSignal(Node node) {
	        /*
	         * If cannot change waitStatus, the node has been cancelled.
	         */
	        if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
	            return false;
	
	        /*
	         * Splice onto queue and try to set waitStatus of predecessor to
	         * indicate that thread is (probably) waiting. If cancelled or
	         * attempt to set waitStatus fails, wake up to resync (in which
	         * case the waitStatus can be transiently and harmlessly wrong).
	         */
	        Node p = enq(node);
	        int ws = p.waitStatus;
	        if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
	            LockSupport.unpark(node.thread); //唤醒休眠线程
	        return true;
    }
	   
	   