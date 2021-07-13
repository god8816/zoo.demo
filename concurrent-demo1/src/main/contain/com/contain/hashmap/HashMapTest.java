package com.contain.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Map map = new HashMap<>(3);
        for(int i=0;i<100;i++) {
        		  map.put(i, i);
        }
        
        for(int i=0;i<100;i++) {
  		  map.put(i, i);
        }
        
        for(int i=0;i<100;i++) {
  		  map.put(i, i);
        }
        
        map.get(1);
        map.get(100);
        map.get(10000);
        map.get(10000);
        map.get(10000);
        map.get(10000);
        map.get(10000);
        map.get(10000);

	}

}
