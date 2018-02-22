package com.arcvideo.sampleplayer;

public class IdManager {
	private static String mCustomIdString = "";
	private static String mContentIdString = "";
	
	public static String getCustomId(String inputUrl){
		if (inputUrl == null || inputUrl.length() == 0) {
			return mCustomIdString;
		}
		
		String sepratorString0 = "/";
		String sepratorString1 = "_";
		int index0 = inputUrl.lastIndexOf(sepratorString0);
		int index1 = inputUrl.indexOf(sepratorString1,index0 +1);
		int index2 = inputUrl.indexOf(sepratorString1, index1 +1);
		if (index1 > index0) {
			mCustomIdString = inputUrl.substring(index0 +1, index1);
		}
		if (index2 > index1) {
			mContentIdString = inputUrl.substring(index1 +1, index2);
		}
		
		return mCustomIdString;
	}
	
	public static String getContentId(String inputUrl){
		if (inputUrl == null || inputUrl.length() == 0) {
			return mContentIdString;
		}
		
		String sepratorString0 = "/";
		String sepratorString1 = "_";
		int index0 = inputUrl.lastIndexOf(sepratorString0);
		int index1 = inputUrl.indexOf(sepratorString1,index0 +1);
		int index2 = inputUrl.indexOf(sepratorString1, index1 +1);
		if (index1 > index0) {
			mCustomIdString = inputUrl.substring(index0 +1, index1);
		}
		if (index2 > index1) {
			mContentIdString = inputUrl.substring(index1 +1, index2);
		}
		
		return mContentIdString;
	}
}
