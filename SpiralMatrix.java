import java.util.*;
/*
* Spiral matrices from leetcode
*/
public class SpiralMatrix {
	// https://leetcode.com/problems/spiral-matrix-ii/
    public static int[][] generateMatrix(int n) {
        int [][] matrix  = new int[n][n];
        if (n <= 0) {
            return matrix;
        }   
        int lowIdxRow  = 0, lowIdxCol = 0;
        int highIdxCol = n-1, highIdxRow = n-1;
        int totalCount = n*n;
        int elemVisited= 0;
        while(elemVisited < totalCount) {
            for(int i=lowIdxCol; i <= highIdxCol; i++) {
                elemVisited++;
                matrix[lowIdxRow][i] = elemVisited;
            }
            lowIdxRow++;
            if(elemVisited == totalCount) {
                break;
            }
            for(int i= lowIdxRow; i <= highIdxRow; i++) {
                elemVisited++;
                matrix[i][highIdxCol] = elemVisited;
            }
            highIdxCol--;
            if(elemVisited == totalCount) {
                break;
            }
            for(int i=highIdxCol; i >= lowIdxCol; i--) {
                elemVisited++;
                matrix[highIdxRow][i] = elemVisited;
            }
            highIdxRow--;
            if(elemVisited == totalCount) {
                break;
            }
            for(int i=highIdxRow; i>= lowIdxRow; i--) {
                elemVisited++;
                matrix[i][lowIdxCol] = elemVisited;
            }          
            lowIdxCol++;
        }
        return matrix;
    }    
    // https://leetcode.com/problems/spiral-matrix/
    public static List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> list = new ArrayList<>();
    	if(matrix == null || matrix.length == 0 || matrix[0].length== 0) {
    		return list;
    	}
        int totalCount = matrix.length * matrix[0].length;
        
        if(totalCount == 0) {
            return list;
        }
        int lowIdxCol, lowIdxRow, highIdxRow, highIdxCol;
        lowIdxCol = lowIdxRow = 0;
        highIdxRow= matrix.length-1;
        highIdxCol= matrix[0].length-1;
        
        
        int elemVisited= 0, i;
        while(elemVisited < totalCount ) {
            for(i= lowIdxCol; i <= highIdxCol; i++) {
                list.add(matrix[lowIdxRow][i]);
                elemVisited++;
            }
            lowIdxRow++;
            if (elemVisited == totalCount) {
            	break;
            }
            for(i = lowIdxRow; i <= highIdxRow; i++) {
                list.add(matrix[i][highIdxCol]);
                elemVisited++;
            }
            highIdxCol--;
            if (elemVisited == totalCount) {
            	break;
            }
            
            for(i= highIdxCol;i >= lowIdxCol; i--) {
                list.add(matrix[highIdxRow][i]);
                elemVisited++;
            }
            highIdxRow--;
            if (elemVisited == totalCount) {
            	break;
            }

            for(i = highIdxRow; i >= lowIdxRow; i--) {
                list.add(matrix[i][lowIdxCol]);
                elemVisited++;
            }
            lowIdxCol++;
            if(elemVisited == totalCount ) {
                break;
            }
        }
        return list;
    }    

	public static void main(String[] args) {
    	int[][] matrix = new int[][]{{1,2,3,4,5},{18,19,20,21,6},
    			{17,28,29,22,7},
    			{16,27,30,23,8},
    			{15,26,25,24,9},
    			{14,13,12,11,10}};
    	List<Integer> list = spiralOrder(matrix);
    	System.out.println(list);

    	//Spiral matrix 2
    	int [][]mat = generateMatrix(4);
    	for(int i=0 ; i < mat.length; i++) {
    		for(int j=0; j < mat[0].length; j++) {
    			System.out.print(mat[i][j] + " ");
    		}
    		System.out.println();
    	}
		
	}

}