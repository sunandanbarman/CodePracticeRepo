import java.util.*;
/**
* Original Q : https://leetcode.com/problems/restore-ip-addresses/
**/
public class FindAllIP {
	public static boolean validate(final String ip) {
	    String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

	    return ip.matches(PATTERN);
	}	
	public static void getAllIP(String header,String rest,int dotCount, List<String> list) {
		if(rest.length() == 0) {
			return;
		}
		if(dotCount == 2) {
			String ip = new StringBuilder(header).append(".").append(rest).toString();
			if (validate(ip))
				list.add(ip);
			return;
		}	
		String partial, possibleIP, extraStr;
		for(int i=0; i < rest.length(); i++) {
			partial = rest.substring(0,i+1);
			extraStr= rest.substring(i+1);
			possibleIP = new StringBuilder(header).append(".").append(partial).toString();
			getAllIP(possibleIP,extraStr,dotCount+1,list);
		}
	}
	public static List<String> generateIPs(String ipAddr) {
        List<String> ipList = new ArrayList<>();
        if (ipAddr == null || ipAddr.length()< 4 || ipAddr.length() > 12) {
            return ipList;
        }		
		String header , restOfString;
		for(int i=0; i < ipAddr.length(); i++) {
			header = ipAddr.substring(0,i+1);
			restOfString = ipAddr.substring(i+1);
			getAllIP(header,restOfString,0,ipList);
		}
		return ipList;
	}

	public static void main(String []args) {
		String ip = "010010";
		List<String> ipList;
		ipList = generateIPs(ip);
		for(String text:ipList) {
			System.out.println(text);
		}

	}

}