import java.util.*;
/**
Original question : https://www.hackerrank.com/challenges/maxsubarray
**/
public class PrintContAndNonContMaxSumSubArray {
    private static void printMaxNonContSubArraySum(int []A) {
    	int sum = 0;
    	boolean allNeg = true;;
    	int largestNegValue = Integer.MIN_VALUE;
    	for(int i=0; i < A.length; i++) {
    		if (A[i] >= 0) {
    			sum += A[i];
    			allNeg = false;
    		} else {
    			if (A[i] > largestNegValue) {
    				largestNegValue = A[i];
    			}
    		}
    	}
    	if (allNeg) {
    		System.out.println(largestNegValue);
    	} else {
    		System.out.println(sum);
    	}
    	
    }
    private static int findMaxContSubArraySum(int []A, int []sum, int idx) {
    	if (idx == 0) {
    		sum[0] = A[0];
    		return A[0];
    	}
    	int prevSum = findMaxContSubArraySum(A, sum, idx-1);
    	sum[idx-1]  = prevSum;
    	sum[idx]    = Math.max(sum[idx-1] + A[idx], A[idx]);
    	return sum[idx];
    }
    private static void printMaxContSubArray(int []A) {
    	int[] sum = new int[A.length];
    	Arrays.fill(sum,0);
    	findMaxContSubArraySum(A, sum, A.length-1);
    	int largest = Integer.MIN_VALUE;
    	for(int i=0; i < sum.length; i++) {
    		if (sum[i] > largest) {
    			largest = sum[i];
    		}
    	}
    	System.out.println(largest);    	
    }
    public static void findMaxValues(int []A) {
    	printMaxContSubArray(A);
    	printMaxNonContSubArraySum(A);

    }
	
	public static void main(String []args) {
		int [] arr = new int[]{1};
		findMaxValues(arr);		
	}
}