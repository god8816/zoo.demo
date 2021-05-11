package com.data.algorithm;

/**
 * 排序类型： 插入排序 
 * 简单介绍：插入排序在排序过程中会把整个数组分成已排好序和还未排序两部分。每次从未排序部分的开头取出一个数字，插入到已排序的部分。
 * 性质： 是稳定的排序法。且最坏的情况下一共要移动(1+2+...+N-1)=(N^2-N)/2次，所以基本是O(n^2)复杂度的排序法，当然输入数据的顺序可以极大的影响该排序算法的复杂度。例如数据本来就是升序排列，所有数据都不用移动，此时只需判断n次就可以结束算法运行
 * */
public class InsertSortDEMO {

	public static void main(String[] args) {
		int[] ins = {100,2,3,5,1,23,6,78,34};
		int[] ins2 = insertSort(ins);
		for(int in: ins2){
			System.out.print(in+",");
		}
	}
	

	public static int[] insertSort(int[] nums){
		for(int i=1;i<nums.length;i++) {
			//为什么i=1，k=i 插入排序从第一个数开始自己与自己比较
			for(int k=i;k>0;k--) {
				if(nums[k]<nums[k-1]) {
					int temp = nums[k-1];
					nums[k-1] = nums[k];
					nums[k] = temp ;
				}
			}
		}
		return nums;
	}
}
