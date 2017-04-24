package com.spyder;

import java.util.ArrayList;

import com.data.CreateWorkBook;
import com.model.Drama;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DoubanSpyder extends Spyder {
	static String LISTURL = "https://movie.douban.com/j/search_subjects?type=tv&tag=%E7%83%AD%E9%97%A8&sort=time&page_limit=_SIZE_&page_start=_SKIP_";
	static String ITEMURL = "https://movie.douban.com/subject/_ID_/";
	
	final static int SIZE = 20;
	
	public static void main(String[] args) {
		String url = LISTURL.replaceAll("_SIZE_", String.valueOf(SIZE));
		String detailURL = ITEMURL;
		int skip = 0;
		
		int time = 3;
		
		int endskip = time*SIZE;
		ArrayList<Drama> dramas = new ArrayList<>();

		while(skip<endskip){
			String listurl = url.replaceAll("_SKIP_", String.valueOf(skip));
			
			String listInfo = excuteGet(listurl);
			
			
			JSONObject object = JSONObject.fromObject(listInfo);
			
			JSONArray dramaList = object.getJSONArray("subjects");

			int size = dramaList.size();
			for(int i=0;i<size;i++){
				Drama drama = new Drama(dramaList.getJSONObject(i));
				detailURL = drama.getURL();
				if (detailURL!=null) {
					String detailHTML = excuteGet(detailURL);
					if (detailHTML!=null) {
						drama.setInfoFromDouban(detailHTML);
					}
				}
				
				dramas.add(drama);
			}
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(listInfo);
			skip +=SIZE;
		}
		
		try {
			CreateWorkBook.write(dramas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
