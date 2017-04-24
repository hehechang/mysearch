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

			//			ִ��execute()����֮��᷵��һ��HttpResponse����,�����������ص�������Ϣ�ͱ�����HttpResponse����.

			//			��ȡ�����������ص�״̬��,�������200��˵���������Ӧ���ɹ���:

			if(httpResponse.getStatusLine().getStatusCode()==200){

				//�������Ӧ���ɹ���

				HttpEntity entity1=httpResponse.getEntity();//����getEntity()������ȡ��һ��HttpEntityʵ��

				String response=EntityUtils.toString(entity1,"utf-8");//��EntityUtils.toString()�����̬������HttpEntityת�����ַ���,��ֹ���������ص����ݴ�������,������ת����ʱ���ַ���ָ����utf-8�Ϳ�����

				return response;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
