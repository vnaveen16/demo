package com.demo.device.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.device.dto.DeviceUserAgentDTO;
import com.demo.device.entity.UserAgentEntity;
@Service
public interface UserAgentService {

	public UserAgentEntity createOrMatchDevice(DeviceUserAgentDTO userAgentDto);
	
	public DeviceUserAgentDTO getUserAgentDetailsById(Long id);
	
	public List<DeviceUserAgentDTO> getAllDevicesByOsName(String osName);
	
	public void deleteAllDevicesById(List<Long> ids);
}
