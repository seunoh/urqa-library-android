package com.urqa.common;

import com.urqa.eventpath.EventPath;

import android.content.Context;

public class StateData {
	
	public static Context 	AppContext = null;
	public static String 	SDKVersion = "0.92";  
	
	public static String 	APIKEY = "";
	
	public static String	SessionID = "";
	
	public static boolean 	FirstConnect = true;
	
	public static boolean 	ToggleLogCat = true;
	//public static 
	
	public static int 		LogLine 		= 20;
	public static boolean 	TransferLog 	= true;
	public static String 	LogFilter 		= "";
	
	public static String	ServerAddress	= "http://ur-qa.com/urqa/"; 
	

}
