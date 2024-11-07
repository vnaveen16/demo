package com.demo.device.parser;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.demo.device.dto.DeviceUserAgentDTO;

@Component
public class DeviceDemoParser {
	
	
	public DeviceUserAgentDTO getUserAgentParseDetails(String userAgent){
		
	
	    String osType = null;
	    String osVersion = null;
	    String browserType = null;
	    String browserVersion = null;
	    
	 
	    try {
	        
	        if (userAgent.indexOf("Windows NT") >= 0) {
	            osType = "Windows";
	            osVersion = userAgent.substring(userAgent.indexOf("Windows NT ")+11, userAgent.indexOf(";"));

	        } else if (userAgent.indexOf("Mac OS") >= 0) {
	            osType = "Mac";
	            osVersion = userAgent.substring(userAgent.indexOf("Mac OS ")+7, userAgent.indexOf(")"));
	           
	        } else if (userAgent.indexOf("X11") >= 0) {
	            osType = "Unix";
	            osVersion = "Unknown";

	        } else if (userAgent.indexOf("android") >= 0) {
	            osType = "Android";
	            osVersion = "Unknown";
	        }

	        if (userAgent.contains("Edge/")) {
	            browserType = "Edge";
	            browserVersion = userAgent.substring(userAgent.indexOf("Edge")).split("/")[1];

	        } else if (userAgent.contains("Safari/") && userAgent.contains("Version/")) {
	            browserType = "Safari";
	            browserVersion = userAgent.substring(userAgent.indexOf("Version/")+8).split(" ")[0];

	        } else if (userAgent.contains("OPR/") || userAgent.contains("Opera/")) {
	            browserType = "Opera";
	            browserVersion = userAgent.substring(userAgent.indexOf("OPR")).split("/")[1];

	        } else if (userAgent.contains("Chrome/")) {
	            browserType = "Chrome"; 
	            browserVersion = userAgent.substring(userAgent.indexOf("Chrome")).split("/")[1];
	            browserVersion = browserVersion.split(" ")[0];

	        } else if (userAgent.contains("Firefox/")) {
	            browserType = "Firefox"; 
	            browserVersion = userAgent.substring(userAgent.indexOf("Firefox")).split("/")[1];
	        }            

	    } catch (Exception ex) {
	    }
	    DeviceUserAgentDTO userAgentDto = new DeviceUserAgentDTO();
	    userAgentDto.setOsNameValue(osType);
	    userAgentDto.setOsVersion(osVersion);
	    userAgentDto.setBrowserName(browserType);
	    userAgentDto.setBrowserVersion(browserVersion);
	    
		return userAgentDto;
	}

}
