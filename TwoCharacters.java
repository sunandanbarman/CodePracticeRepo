import java.util.*;
/**
* https://www.hackerrank.com/challenges/two-characters
**/
public class TwoCharacters {
    private static HashSet<Character> getAllAlphabets(String str) {
        HashSet<Character> set = new HashSet<>();
        for(int i=0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!set.contains(ch)) {
                set.add(ch);                   
            }
        }
        return set;
    }
    private static List<String> getStringSet(HashSet<Character> set) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(Character ch : set) {
            sb.append(ch);
        }
        String newStr = "";
        for(int i=0; i < sb.length(); i++) {
            for(int j=i+1; j < sb.length(); j++) {
                newStr = new StringBuilder(Character.toString (sb.charAt(i))).append(sb.charAt(j)).toString();  
                list.add(newStr);
            }
        }
        return list;
    }
    private static String getStringWithoutSomeCharacter(String str, String perm) {
        char ch1 = perm.charAt(0);
        char ch2 = perm.charAt(1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == ch1 || ch == ch2) {
                sb = sb.append(ch);
            }
        }
        return sb.toString();
    }
    private static boolean checkIsStringValid(String str) {
        if(str == null || str.length() <= 1) {
            return true;
        }
        String twoStr = str.substring(0,2);
        for(int i=2; i < str.length(); i=i+2) {
            if(str.length()-i == 1) { //one character left
                if(str.charAt(str.length()-1) != twoStr.charAt(0))
                    return false;
                return true;
            }
            if(!str.substring(i,i+2).equals(twoStr))
                return false;
        }
        return true;
    }
    private static int getMaxLengthString(String str) {
        int length = 0;
        if(str == null || str.length() == 0) {
            return length;
        }
        HashSet<Character> set = getAllAlphabets(str);
        List<String> permList  = getStringSet(set);
        int maxLength = 0;
        for(String perm: permList) {
            String possibleStr = getStringWithoutSomeCharacter(str, perm);
            if(checkIsStringValid(possibleStr)) {
                if(possibleStr.length() > maxLength)
                    maxLength = possibleStr.length();
            }
        }
        return maxLength;
    }
    public static void main(String[] args) {
        System.out.println("Enter string");
        Scanner in = new Scanner(System.in);

        String s = in.next();
        System.out.println(getMaxLengthString(s));
    }
}
