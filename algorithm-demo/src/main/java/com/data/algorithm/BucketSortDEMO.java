package com.data.algorithm;

/**
 * 排序类型： 桶排序
 * 最优：所有数据平均在分配在每个桶
 * 最差：所有数据集中在一个桶，此类场景可以使用 计数排序
 * 算法点评：实现简单，算法稳定，时间复杂度低O(n)，空间复杂度比计数排序高O(max+1),是一个完美的算法
 * */
public class BucketSortDEMO {

	public static void main(String[] args) {
		int[] ins = {1,2,4,3,0,100,4567,3424,1,2,45};
        bucketSort(ins);
        for (int i : ins) {
            System.out.print(i+" ");
        }
	}
	
	
    public static void bucketSort(int[] ins){
        if (ins == null || ins.length < 2){
            return;
        }

        int max = Integer.MIN_VALUE;

        //1.遍历找到最大值
        for (int i = 0; i < ins.length; i++) {
            max = Math.max(ins[i],max);
        }

        //2.因为考虑正整数，所以需要max+1个桶，一个桶代表一个数值，并不是范围，对于大量数据排序这个地方最好使用链式结构，这样有利于内存利用率，但是加大复杂度和降低查询效率
        int[] bucket = new int[max + 1];
        
        //3.放数入桶的操作,相同的数放在同一个桶中
        for (int i = 0; i < ins.length; i++){
            bucket[ins[i]]++;
        }

        int i = 0;
        //4.遍历桶
        for (int j = 0; j < bucket.length; j++) {
            //5.用for while迭代一个桶元素的个数，bucket[j]就是指元素出现的次数。
        	   //语意等于： while (bucket[j]-->0 ){} 
            	for (int k = 0; k < bucket[j]; ++k) {
                ins[i++] = j;
            }
        }
    }

}
