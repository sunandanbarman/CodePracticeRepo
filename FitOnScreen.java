import java.util.*;

/**
* http://massivealgorithms.blogspot.com/2016/10/leetcode-418-sentence-screen-fitting.html
**/
class FitOnScreen {    
    private static int findFitOnScreen(String []sentenceRows, int row, int col) {
    	if(sentenceRows == null || sentenceRows.length == 0){
    		return 0;
    	}
    	if(row <= 0 || col <= 0) {
    		return 0;
    	}
    	int result = 0;
    	int spaceLeft, noOfWordFit =0, wordCounter =0;
    	int[] posn = new int[row];
    	Arrays.fill(posn,0);
    	String word;
    	for(int i=0;i < row;) {
    		word = sentenceRows[wordCounter];    		
			spaceLeft = col -  ( word.length() + posn[i] );
			if(spaceLeft >=0 ) {
				noOfWordFit++;
				posn[i] += word.length() +1;
				if(spaceLeft == 0)
					i++; // no more space left, move on...
			} else {
				i++; // move to next row
			}
			wordCounter = (wordCounter + 1) % sentenceRows.length;
			if(noOfWordFit == sentenceRows.length) {
				result++;
				noOfWordFit = 0;
			}
    	}
    	return result;
    }
    public static void main(String[] args) {
        String test= "hello world";
        System.out.println(findFitOnScreen(test.split(" "),2,8));        
    }
}