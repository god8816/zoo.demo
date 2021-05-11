package com.data.algorithm;

/**
 * 排序类型： 冒泡排序
 * 
 * */
public class BubbleSortDEMO {

	public static void main(String[] args) {
		Integer[] ins = {100,2,3,5,1,23,6,78,34};
		Integer[] ins2 = bubbleSort(ins);
		for(int in: ins2){
			System.out.print(in+",");
		}
	}
	

	public static Integer[] bubbleSort(Integer[] nums){
		for(int i=1;i<nums.length;i++) {
			//i=1 是右边元素，k=0是右边的元素，他们的长度小于nums.length,左右移位比较
			for(int k=0;k<nums.length-i;k++) {	
				if(nums[k]>nums[k+1]) {
					 //任何两个元素比较必须有中间变量保存中间值 防止覆盖
					 int temp = nums[k];
					 nums[k] = nums[k+1];
					 nums[k+1]=temp;
				}
			}
			
		}
		return nums;
	}
}
