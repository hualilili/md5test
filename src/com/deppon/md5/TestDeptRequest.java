
    package com.deppon.md5; 

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
   /** 
 * @Title: TestDeptRequest.java
 * @Package com.deppon.md5 
 * @Description: TODO(添加描述) 
 * @author 林其刚 
 * @date 2014-8-25 下午3:43:17 
 * @version V1.0 
 */
public class TestDeptRequest {

	/** 
	 * @Title: main 
	 * @Description:TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args 设定文件 
	 * @returnvoid 返回类型 
	 * @throws 
	 * @author 林其刚 
	 * @date 2014-8-25 下午3:43:17 
	 */

	public static void main(String[] args) {
		String url = "http://10.224.70.133:8080/ula-resource-web/dept/search.json?deptName=";
        PostMethod postMethod = new PostMethod(url);
        
        StringPart sp = new StringPart("deptName", "开发");
        
         MultipartRequestEntity mrp= new MultipartRequestEntity(new Part[]{sp}, postMethod
                .getParams());
        postMethod.setRequestEntity(mrp);
        
        //执行postMethod
        HttpClient httpClient = new HttpClient();
         try {
           httpClient.executeMethod(postMethod);
        } catch (HttpException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
       }


	}

}

