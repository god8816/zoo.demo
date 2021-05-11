package com.data.algorithm;

/**
 * 排序类型： 选择排序
 * 
 * */
public class SelectSortDEMO {

	public static void main(String[] args) {
		Integer[] ins = {100,2,3,5,1,23,6,78,34};
		Integer[] ins2 = selectSort(ins);
		for(int in: ins2){
			System.out.print(in+",");
		}
	}
	

	public static Integer[] selectSort(Integer[] nums){
		for(int i=0;i<nums.length;i++) {
			int min_i=i;
			//k初始化为什么是k=i+1，而不是0？因为算法角度第一遍0是自己，无需再次比较
			for(int k=i+1;k<nums.length;k++) {
				//k每轮都和最小值min_i比较大小
				if(nums[k]<nums[min_i]) {
					//记录每轮最小的i
					min_i = k;
				}
			}
			//中间变量初始化为第一个元素
			int tmp = nums[i];
			//将最小的元素赋值给第一个元素
			nums[i]=nums[min_i];
		    //将最小元素的空位置填充第一个元素，完成比较的最小元素和最大元素的位置交换
			nums[min_i] = tmp;
		}
		return nums;
	}
}
