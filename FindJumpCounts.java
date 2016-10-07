import java.util.*;
/**
* CTCI Question 8.1 implementation
**/
public class FindJumpCounts {
	private static int findCountsOfJumps(int n,int i, int[] memo){
		int tempNode, pathSum=  0;
		int[] arr = new int[]{1,2,3};
		for (int a: arr) {
			tempNode = i+a;
			int x = n - tempNode;
			if (x < 0) {
				continue;
			}
			if (x < memo.length && memo[x] != -1) {
				pathSum = pathSum + memo[x];
			} else if (x == 0) {
				pathSum++;
			} 
			else {
				int pathSumForRemaining = 0;
				for(int temp : arr) {
					pathSumForRemaining += findCountsOfJumps(x,temp,memo);
				}
				memo[x] = pathSumForRemaining;
				pathSum = pathSum + memo[x];
			}
		}
		return pathSum;

	}
	public static int findCount(int n) {
		if (n <= 0) {
			return 0;
		}
		int[] memo = new int[n+1];
		Arrays.fill(memo,-1);

		memo[1] = 1;
		memo[2] = 2;
		memo[3] = 4;
		if (n >=1 && n <= 3) {
 			return memo[n];
		}
		int sum = 0;
		for (int i=1; i<=3; i++ ) {
			sum += findCountsOfJumps(n,i,memo);
		}
		return sum;
	}
	public static void main(String []args) {
		System.out.println(findCount(6));

	}	

}