package com.base.spi;

import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * 基于配置加载的接口迭代器
 * 文档：https://hollischuang.github.io/toBeTopJavaer/#/basics/java-basic/spi-principle
 * */
public class SpiTest {

	public static void main(String[] args) throws Exception {
	    // spi 加载
		ServiceLoader<Izoo> zooList = ServiceLoader.load(Izoo.class);
		Izoo zoo = StreamSupport.stream(zooList.spliterator(), false).filter(e -> e.getClass().getName().equals("com.base.spi.Cat")).findFirst().orElseThrow(()->new Exception("未匹配类型"));
		zoo.name();
	}

}
