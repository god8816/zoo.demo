package com.es.createindex.demo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

	private String orderId;
	
	private String orderName;
	
	private List<OrderDetail> orderDetailList;
}
