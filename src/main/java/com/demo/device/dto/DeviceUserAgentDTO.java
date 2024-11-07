package com.demo.device.dto;

import java.util.Objects;

import com.demo.device.entity.UserAgentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class DeviceUserAgentDTO {
	
	 private Long deviceId;
	 private int hitCount;
	 private String osNameValue;
	 private String osVersion;
	 private String browserName;
	 private String browserVersion;

	 @Override
	 public int hashCode() {
	     return Objects.hash(this.osNameValue, this.osVersion, this.browserName, this.browserVersion);
	 }
	 @Override  
	 public boolean equals(Object obj)   
	 {  
	 if (obj == null)   
	 return false;  
	 if (obj == this)  
	 return true;  
	 return this.osNameValue == ((DeviceUserAgentDTO) obj).osNameValue &&
			this.osVersion == ((DeviceUserAgentDTO) obj).osVersion &&
			this.browserName == ((DeviceUserAgentDTO) obj).browserName &&
			this.browserVersion == ((DeviceUserAgentDTO) obj).browserVersion;  
	 }  
}
