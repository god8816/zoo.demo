来源历史：
  LockSupport类是Java6(JSR166-JUC)引入
  
类解释：LockSupport
  1: LockSupport.park();   锁定
  2: LockSupport.unpark(); 解除锁定

解决问题：
  1： 解决线程阻塞与唤醒，LockSupport是jdk比较底层的类，在并发包到处都有LockSupport实现案例，其功能与wait,notify相似。
  2： 场景区别：wait,notify只能在对象获取锁的时候使用，LockSupport可以在任何场景使用。
  
方法剖析：
  涉及Unsafe方法，后续研究  
  
其他博文：
  1:Unsafe类 park和unpark方法解析: https://blog.csdn.net/a7980718/article/details/83661613
  2:阻塞和唤醒线程——LockSupport功能简介及原理浅析: https://www.cnblogs.com/takumicx/p/9328459.html
  3:柱塞线程类型讲解 https://www.cnblogs.com/tong-yuan/p/11768904.html