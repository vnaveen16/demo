package com.demo.device.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.device.entity.UserAgentEntity;

@Repository
public interface UserAgentRepository extends CrudRepository<UserAgentEntity, Long> {
	
	UserAgentEntity findByDeviceId(Long deviceId);
	
	UserAgentEntity findByOsNameValueAndOsVersionAndBrowserNameAndBrowserVersion(String osNameValue, String osVersion, String browserName, String browserVersion);
	
	List<UserAgentEntity> findDeviceByOsNameValue(String osNameValue);
	
}
