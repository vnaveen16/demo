package com.demo.device.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.device.dto.DeviceUserAgentDTO;
import com.demo.device.entity.UserAgentEntity;
import com.demo.device.repository.UserAgentRepository;

@Service
public class UserAgentServiceImpl implements UserAgentService{


	private UserAgentEntity userAgentEntity;
	@Autowired
	private UserAgentRepository userAgentRepository;
	
	public UserAgentEntity createOrMatchDevice(DeviceUserAgentDTO userAgentDto) {
		
		UserAgentEntity userAgentEntityMatched = userAgentRepository.findByOsNameValueAndOsVersionAndBrowserNameAndBrowserVersion(userAgentDto.getOsNameValue(), userAgentDto.getOsVersion(), userAgentDto.getBrowserName(), userAgentDto.getBrowserVersion());
		if(userAgentEntityMatched!=null) {
			userAgentEntityMatched.setHitCount(userAgentEntityMatched.getHitCount()+1);
			return userAgentRepository.save(userAgentEntityMatched);
		}else {
		userAgentEntity = new UserAgentEntity();
		userAgentEntity.setBrowserName(userAgentDto.getBrowserName());
		userAgentEntity.setBrowserVersion(userAgentDto.getBrowserVersion());
		userAgentEntity.setOsNameValue(userAgentDto.getOsNameValue());
		userAgentEntity.setOsVersion(userAgentDto.getOsVersion());
		userAgentEntity.setHitCount(1);
		    return userAgentRepository.save(userAgentEntity);
		}
		
     }
	
	public DeviceUserAgentDTO getUserAgentDetailsById(Long id) {
		DeviceUserAgentDTO deviceUserAgentDTO = new DeviceUserAgentDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(userAgentRepository.findByDeviceId(id), deviceUserAgentDTO);
		return deviceUserAgentDTO;
	}
	
	public List<DeviceUserAgentDTO> getAllDevicesByOsName(String osName) {
		List<UserAgentEntity> userAgentList = userAgentRepository.findDeviceByOsNameValue(osName);
		List<DeviceUserAgentDTO> userAgentDTOList = new ArrayList<>();
		userAgentList.forEach(userAgentEntity->{
		  
			  ModelMapper modelMapper = new ModelMapper();
			  DeviceUserAgentDTO deviceUserAgentDTO = new DeviceUserAgentDTO();
			  modelMapper.getConfiguration().setAmbiguityIgnored(true);
			  modelMapper.map(userAgentEntity, deviceUserAgentDTO);
			  userAgentDTOList.add(deviceUserAgentDTO);
		});
		return userAgentDTOList;
	}
	
	public void deleteAllDevicesById(List<Long> ids) {
		userAgentRepository.deleteAllById(ids);
	}
}
