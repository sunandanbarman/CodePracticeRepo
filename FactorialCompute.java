import java.math.BigInteger;
import java.util.*;
/**
Original question : https://www.hackerrank.com/challenges/fibonacci-modified
**/
public class FactorialCompute {
	private static BigInteger compute(int t1, int t2,int n, BigInteger[] memo,BigInteger negOne) {
		if (n== 1 || n == 2) {
			return n == 1 ? BigInteger.valueOf(t1) : BigInteger.valueOf(t2);
		}
		if ( n < memo.length && !memo[n].equals(negOne)) {
			return memo[n];
		}
		BigInteger n2Value = memo[n-2];
		if (n2Value.equals(negOne)) {
			n2Value = compute(t1,t2,n-2,memo,negOne);
			memo[n-2] = n2Value;
		}
		BigInteger n1Value = memo[n-1];
		if (n1Value.equals(negOne)) {
			n1Value = compute(t1,t2,n-1,memo,negOne);
			memo[n-1] = n1Value;
		}
		return n1Value.pow(2).add(n2Value);
		
		
	}
	//tn = tn-1^2 + tn-2
	public static void computeModifiedFibonacci(int t1, int t2, int n) {
		BigInteger[] arr = new BigInteger[n+1];
		BigInteger negOne= BigInteger.ONE.negate();
		Arrays.fill(arr, 0, n+1, negOne);
		
		System.out.println(compute(t1,t2,n,arr,negOne));
		
	}
	
	public static void main(String []args ) {
		computeModifiedFibonacci(0,1, 5);
	}
}