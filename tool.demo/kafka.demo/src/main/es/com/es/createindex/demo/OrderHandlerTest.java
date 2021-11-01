package com.es.createindex.demo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;


public class OrderHandlerTest {

	public static void main(String[] args) {
		insertOrderAndDetail();
	}
	
	public static void insertOrderAndDetail() {
		
		for(int o = 1;o<=10;o++) {
			Order order = new Order();
			order.setOrderId(String.valueOf(o));
			order.setOrderName("订单名称"+Long.valueOf(o));
			
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			for(int od =1;od<=4;od++) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderId(Long.valueOf(o));
				orderDetail.setOrderDetailId(String.valueOf(o)+"-"+String.valueOf(od));
				orderDetail.setOrderDetailName("订单详情名称"+String.valueOf(o)+"-"+String.valueOf(od));
				orderDetailList.add(orderDetail);
			}
			order.setOrderDetailList(orderDetailList);
			//插入es
			insertEs(order);
		}
	}
	
	
	public static void insertEs(Order order) {
		//ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		System.out.println("ES-createOrder入参：" + JSON.toJSONString(order));
		//String msg = clientUtil.addDocument("order_index_test","order_index_test",order);
		//System.out.println(msg);
	}
}
