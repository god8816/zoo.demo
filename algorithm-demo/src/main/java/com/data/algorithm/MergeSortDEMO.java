package com.data.algorithm;

/**
 * 排序类型： 并归排序
 * 最优：O(n)  
 * 最差时间复杂度：O(nlogn) 
 * 平均时间复杂度：O(nlogn) 
 * 空间复杂度：O(n)
 * 算法点评：结构复杂，效率一般
 * */
public class MergeSortDEMO {

	public static void main(String[] args) {
		int[] ins = {100,2,3,5,1,23,6,78,34,2345,4,67,1,1,467,56};
		mergeSort(ins);
		for(int in: ins){
			System.out.print(in+",");
		}
	}
	
	/**
	 * 归并排序入口
	 * */
	public static void mergeSort(int[] array) {
	        if (array == null || array.length == 0)
	            return;
	        //申请缓存空间
	        int[] temp = new int[array.length];
	        mergeSort(array, 0, array.length - 1, temp);
	 }
	    
	
	//三次归并
	private static void mergeSort(int array[], int first, int last, int temp[]) {
	        if (first < last) {
	            int mid = (first + last) / 2;
	            //1.递归归并左边元素
	            mergeSort(array, first, mid, temp); 
	            //2.递归归并右边元素
	            mergeSort(array, mid + 1, last, temp); 
	            //3.再将二个有序数列合并
	            mergeArray(array, first, mid, last, temp); 
	        }
	}
	 
    /**
     * 合并两个有序数组
     * array[first]~array[mid]为第一组
     * array[mid+1]~array[last]为第二组
     * temp[]为存放两组比较结果的临时数组
     */
    private static void mergeArray(int array[], int first, int mid, int last, int temp[]) {
        int i = first, j = mid + 1; //i为第一组的起点, j为第二组的起点
        int m = mid, n = last; //m为第一组的终点, n为第二组的终点
        int k = 0; //k用于指向temp数组当前放到哪个位置
        
        //1.将两个有序序列循环比较, 填入数组temp
        while (i <= m && j <= n) { 
            if (array[i] <= array[j])
                temp[k++] = array[i++];
            else
                temp[k++] = array[j++];
        }
        
       //2.如果比较完毕, 第一组还有数剩下, 则全部填入temp
        while (i <= m) { 
            temp[k++] = array[i++];
        }
        
        //3.如果比较完毕, 第二组还有数剩下, 则全部填入temp
        while (j <= n) {
            temp[k++] = array[j++];
        }
        
        //4.将排好序的数填回到array数组的对应位置
        for (i = 0; i < k; i++) {
            array[first + i] = temp[i];
        }
    }

}
