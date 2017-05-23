
    package com.deppon.md5; 

import java.net.URLEncoder;


   /** 
 * @Title: Test.java
 * @Package com.deppon.md5 
 * @Description: TODO(娣诲姞鎻忚堪) 
 * @author 鏋楀叾鍒� 
 * @date 2014-8-22 涓嬪崍5:43:49 
 * @version V1.0 
 */
public class Test {

	/** 
	 * @Title: main 
	 * @Description:TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔鐢�) 
	 * @param @param args 璁惧畾鏂囦欢 
	 * @returnvoid 杩斿洖绫诲瀷 
	 * @throws 
	 * @author 鏋楀叾鍒� 
	 * @date 2014-8-22 涓嬪崍5:43:49 
	 */

	public static void main(String[] args) {
		try {
			System.out.println(CryptoUtil.digestByMD5("qqqqqq"));
//		    System.out.println(URLEncoder.encode("eadbCVWJGYAH7Kng4U1fcw=="));
		} catch (Exception e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			
		}

		// TODO Auto-generated method stub 

	}

}

