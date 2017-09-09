package com.xyz.client;

import com.xyz.business.RSSFeedReader;

public class RSSFeedClient {
	public static void main(String[] args) {
		
		
		// Send the RSS Feed URLto common RSS reader 
		String status = RSSFeedReader.readRSS("http://tech.uzabase.com/rss");
		
		if(status.equals("OK")){
			System.out.println("Feed update done & copied in the file.");
		}

	}

}
