package com.spyder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Spyder {

	public static String excuteGet(String URL){

		try {
			HttpClient httpClient=HttpClients.createDefault();
			HttpGet httpGet=new HttpGet(URL);
			//"https://movie.douban.com/j/search_subjects?type=tv&tag=%E7%83%AD%E9%97%A8&sort=time&page_limit=20&page_start=20"
			//https://movie.douban.com/subject/26996841/
			HttpResponse httpResponse = httpClient.execute(httpGet);

			//			执行execute()方法之后会返回一个HttpResponse对象,服务器所返回的所有信息就保护在HttpResponse里面.

			//			先取出服务器返回的状态码,如果等于200就说明请求和响应都成功了:

			if(httpResponse.getStatusLine().getStatusCode()==200){

				//请求和响应都成功了

				HttpEntity entity1=httpResponse.getEntity();//调用getEntity()方法获取到一个HttpEntity实例

				String response=EntityUtils.toString(entity1,"utf-8");//用EntityUtils.toString()这个静态方法将HttpEntity转换成字符串,防止服务器返回的数据带有中文,所以在转换的时候将字符集指定成utf-8就可以了

				return response;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
