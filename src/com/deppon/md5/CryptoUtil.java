
    package com.deppon.md5; 
   /** 
 * @Title: CryptoUtil.java
 * @Package com.deppon.foss.module.permission.server.action 
 * @Description: TODO(添加描述) 
 * @author 林其刚 
 * @date 2014-8-22 下午5:27:48 
 * @version V1.0 
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil
{

  public static String base64Encode(byte[] bytes)
  {
    if (bytes == null) {
      return "";
    }
    return new BASE64Encoder().encode(bytes);
  }

  public static String digestByMD5(String text) throws Exception {
    if (text == null)
      return null;
    byte[] digest = new byte[0];
    try {
      digest = md5(text.getBytes());
      return base64Encode(digest);
    } catch (NoSuchAlgorithmException e) {
      throw new Exception(e);
    }
  }

  private static byte[] md5(byte[] input) throws NoSuchAlgorithmException {
    MessageDigest alg = MessageDigest.getInstance("MD5");
    alg.update(input);
    byte[] digest = alg.digest();
    return digest;
  }

}

