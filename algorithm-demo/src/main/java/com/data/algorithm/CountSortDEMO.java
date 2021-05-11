package com.data.algorithm;

/**
 * 排序类型： 计数排序 
 * 性质： 
 * */
public class CountSortDEMO {

	public static void main(String[] args) {
		int[] ins = {100,2,2,2,2,3,5,1,23,6,6,6,6,78,34};
		int[] ins2 = countSort(ins);
		for(int in: ins2){
			System.out.print(in+",");
		}
	}
	

	/**
	 * 计数排序
	 * */
	public static int[] countSort(int[] ins) {
	    //1.找出数组A中的最大值、最小值
	    int max = Integer.MIN_VALUE;
	    int min = Integer.MAX_VALUE;
	    for (int num : ins) {
	        max = Math.max(max, num);
	        min = Math.min(min, num);
	    }
	    //2.初始化计数数组count，此算法保证有足够的中间堆空间
	    int[] count = new int[(max-min+1)+1];
	    //3.对计数数组各元素赋值，count[0]永远为0
	    for (int num : ins) {
	        //4.统计每个数组节点出现的次数
	        count[num-min+1]++;
	    }
	    //5.计数数组变形，新元素的值是前面元素累加之和的值
	    for (int i=1; i<count.length; i++) {
	        count[i] += count[i-1];
	    }
	    //6.创建结果数组
	    int[] result = new int[ins.length];
	    //7.遍历A中的元素，填充到结果数组中去，从前往后遍历
	    for (int j=0; j<ins.length; j++) {
	    	    //8.目标值和最小值之间的差距
	     	int disValue = ins[j]-min;
	     	//9.求低N组累加之和的值
	     	int heValue = count[disValue];
	     	result[heValue] = ins[j];
	        count[disValue]++;
	    }
	    return result;
	}
}
