package com.concurrent.threadlocal;

import java.lang.ref.WeakReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * WeakReference 弱引用demo
 * */
public class WeakReferenceTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		User user = new WeakReferenceTest().new User(new WeakReferenceTest().new HeiRen("小黑鬼",123));
		System.gc();
		//等待GC完成Heidi
		//Thread.sleep(10000);
		//HeiRen对象被回收了，如果不被回收加大sleep时间，注意string 类型不会被回收比如 WeakReference<String> 因为其入常量池
		System.out.println( user.get());
		//System.out.println(user.get().getName());
	
	}
	
	
	//构建弱应用对象User
	@Getter
	@Setter
	class User extends WeakReference<HeiRen>{
		public User(HeiRen heiRen) {
			super(heiRen);
		}
		
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	class HeiRen{
		private String name;
		private Integer age;
	}

}
