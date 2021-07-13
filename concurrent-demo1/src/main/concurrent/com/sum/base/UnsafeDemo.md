来源历史：
  1：JDK1.8及以前Unsafe的包路径 package sun.misc; 限制不推荐调用
  2：JDK9及以后Unsafe的包路径 jdk.internal.misc;  可以直接使用
  
使用方式：
  1：JDK1.8及以前，通过反射调用
     Field f = Unsafe.class.getDeclaredField("theUnsafe");
     f.setAccessible(true);
     Unsafe unsafe = (Unsafe) f.get(null);
  2：JDK9及以后，直接调用
     public static Unsafe getUnsafe() {
        return theUnsafe;
     }
  
解决问题：
  Unsafe提供的API大概分为内存操作、CAS、Class相关、对象操作、线程调度、系统信息获取、内存屏障、数组操作等几类
  
方法剖析：
  内存操作：
	    //分配内存, 类似C++的malloc函数  (demo: UnsafeDemo.allocateMemory();)
		public native long allocateMemory(long bytes);
		//扩充内存  (demo: UnsafeDemo.reallocateMemory();)
		public native long reallocateMemory(long address, long bytes);
		//释放内存  (demo: UnsafeDemo.freeMemory(address))
		public native void freeMemory(long address);
		//给指定的内存地址块中设置值 (o: 待存储对象 offset：)
		public native void setMemory(Object o, long offset, long bytes, byte value);
		//内存拷贝
		public native void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes);
		//获取给定地址值，忽略修饰限定符的限制访问限制。相似操作: getFloat,getInt，getDouble，getLong，getChar,getByte等
		public native Object getObject(Object o, long offset);
		//为给定地址设置值，忽略修饰限定符的访问限制，相似操作: putInt,putDouble，putLong，putChar,putByte等
		public native void putObject(Object o, long offset, Object x);
		//获取给定地址的byte类型的值(当且仅当该内存地址为allocateMemory分配时，此方法结果为确定的)
		public native byte getByte(long address);
   CAS相关：



  
其他博文：
  1：http://blog.jerkybible.com/2017/07/22/Unsafe%E7%B1%BB%E5%88%9D%E6%8E%A2/
  2：https://blog.csdn.net/ccc292811552/article/details/89672198