package com.data.algorithm;

/**
 * 排序类型： 快速排序 
 * 性质： 
 * */
public class QuickSortDEMO {

	public static void main(String[] args) {
		int[] ins = {100,2,3,5,1,23,6,78,34};
		int[] ins2 = quickSort(ins,0,8);
		for(int in: ins2){
			System.out.print(in+",");
		}
	}
	

	/**
	 * s 待排序数组
	 * markNum1: 基准数1 取数组的起始位置
	 * markNum2：基准数2 取数组的结束位置
	 * 提高基准数数目可以降低遍历深度 
	 * */
	public static int[] quickSort(int s[], int rightNum, int leftNum)
	{
	    if (rightNum < leftNum)
	    {
	    	    //右基准变量
	        int i = rightNum, 
	        		//左基准变量
	        	    j = leftNum,
	        	    //中间变量
	        	    temp = s[rightNum];
	        while (i < j)
	        {
	          	//从右向左找第一个小于temp的数
	            while(i < j && s[j] >= temp) 
	                j--;  
	            if(i < j) 
	                s[i++] = s[j];
	            
	            //从左向右找第一个大于等于temp的数
	            while(i < j && s[i] < temp) 
	                i++;  
	            if(i < j) 
	                s[j--] = s[i];
	        }
	        s[i] = temp;
	        quickSort(s, rightNum, i - 1); // 递归调用 
	        quickSort(s, i + 1, leftNum);
	    }
	    return s;
	}
}
