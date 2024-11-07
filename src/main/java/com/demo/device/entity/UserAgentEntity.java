package com.demo.device.entity;

import java.util.Objects;

import com.demo.device.dto.DeviceUserAgentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name="user_agent")
public class UserAgentEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long deviceId;
    
    @Column(name="hit_count", nullable=false, unique=false)
    private int hitCount;
    
    @Column(name="os_name", nullable=false, unique=false)
    private String osNameValue;

    @Column(name="os_version", nullable=false, unique=false)
    private String osVersion;
    
    @Column(name="browser_name", nullable=false, unique=false)
    private String browserName;
    
    @Column(name="browser_version", nullable=false, unique=false)
    private String browserVersion;

    /*@Override
	 public int hashCode() {
	    //return getClass().hashCode(); 
    	return Objects.hash(this.osName, this.osVersion, this.browserName, this.browserVersion);
	 }
    @Override
    public boolean equals(Object obj) {
    	if (obj == null)   
    		return false; 
        if (this == obj) 
        	return true;
        if (!(obj instanceof UserAgentEntity))
            return false;
 
 
        return  this.deviceId==((UserAgentEntity) obj).getDeviceId() &&
    			this.osName.equals(((UserAgentEntity) obj).getOsName());
    }*/
 
}
