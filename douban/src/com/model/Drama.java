package com.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

public class Drama {
	String name;
	Double rate;
	String nation;
	HashSet<String> type;
	Date date;
	String id;
	String imgURL;
	String URL;
	
	public Drama(JSONObject jsonObject){
		this.rate = getDoubleFromJSONObject(jsonObject,"rate");
		this.name = getStringFromJSONObject(jsonObject,"title");
		this.id = getStringFromJSONObject(jsonObject,"id");
		this.imgURL = getStringFromJSONObject(jsonObject,"cover");
		this.URL = getStringFromJSONObject(jsonObject,"url");
		this.type = new HashSet<>();
	}
	
	public void setInfoFromDouban(String detailHTML){
		Document document = Jsoup.parse(detailHTML);
		
		Element info = document.getElementById("info");
		Elements genres = info.getElementsByAttributeValue("property", "v:genre");
		for (Element genre : genres) {
			type.add(genre.text().trim());
		}
		
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Matcher matcher;
		String infoText = info.text();
		infoText = infoText.replaceAll("(\\S+:)", "_SPLIT_$1");
		String[] infos = infoText.split("_SPLIT_");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (String itemInfo : infos) {
			try {
				int k = itemInfo.indexOf(":");
				String a = itemInfo.substring(0,k);
				String b = itemInfo.substring(k+1);
				if (a.contains("Ê×²¥")) {
					matcher = pattern.matcher(b);
					Date date = null;
					while(matcher.find()){
						String time = matcher.group();
						Date d = format.parse(time);
						if (date==null||d.before(date)) {
							date = d;
						}
					}
					this.date = date;
				}else if (a.contains("¹ú¼Ò")) {
					this.nation = b;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	static String getStringFromJSONObject(JSONObject jsonObject,String key){
		try {
			return jsonObject.getString(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	static Double getDoubleFromJSONObject(JSONObject jsonObject,String key){
		try {
			return jsonObject.getDouble(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public HashSet<String> getType() {
		return type;
	}

	public void setType(HashSet<String> type) {
		this.type = type;
	}
	
}
