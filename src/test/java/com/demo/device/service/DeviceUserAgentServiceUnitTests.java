package com.demo.device.service;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.device.dto.DeviceUserAgentDTO;
import com.demo.device.entity.UserAgentEntity;
import com.demo.device.repository.UserAgentRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeviceUserAgentServiceUnitTests {
    @Mock
    private UserAgentRepository userAgentRepository;

    @InjectMocks
    private UserAgentServiceImpl userAgentService;

    com.demo.device.entity.UserAgentEntity userAgentEntity;

    com.demo.device.dto.DeviceUserAgentDTO deviceUserAgentDTO;

    @BeforeEach
    public void setup(){

        userAgentEntity = UserAgentEntity.builder()
                //.deviceId(352L)
                .osNameValue("Windows")
                .osVersion("10.0")
                .browserName("Chrome")
                .browserVersion("130.0.0.0")
                .hitCount(1)
                .build();

        deviceUserAgentDTO = DeviceUserAgentDTO.builder()
                //.deviceId(352L)
                .osNameValue("Windows")
                .osVersion("10.0")
                .browserName("Chrome")
                .browserVersion("130.0.0.0")
                //.hitCount(7)
                .build();

    }

    @Test
    @Order(1)
    public void saveDeviceUserAgentTest(){
        // precondition
        //userAgentEntity.setHitCount(8);
        given(userAgentRepository.save(userAgentEntity)).willReturn(userAgentEntity);
        //given(userAgentService.createOrMatchDevice(deviceUserAgentDTO)).willReturn(userAgentEntity);
        /*
        action
        deviceUserAgentDTO.
        */
        UserAgentEntity savedUserAgent = userAgentService.createOrMatchDevice(deviceUserAgentDTO);

        // verify the output
        assertNotNull(savedUserAgent);
    }

    @Test
    @Order(2)
    public void getDeviceUserAgentById(){
    	userAgentEntity.setDeviceId(352L);
        // precondition
        given(userAgentRepository.findByDeviceId(352L)).willReturn(userAgentEntity);

        // action
        DeviceUserAgentDTO existingEntity = userAgentService.getUserAgentDetailsById(352L);

        // verify
        assertNotNull(existingEntity);

    }
    
    @Test
    @Order(3)
    public void getAllDeviceUserAgentListByOsNameTest(){
    	UserAgentEntity userAgentEntity1 = UserAgentEntity.builder()
                .deviceId(353L)
                .osNameValue("Windows")
                .osVersion("10.0")
                .browserName("Firefox")
                .browserVersion("132.0")
                .build(); 
        // precondition
        given(userAgentRepository.findDeviceByOsNameValue("Windows")).willReturn(List.of(userAgentEntity,userAgentEntity1));

        // action
        List<DeviceUserAgentDTO> dtoList = userAgentService.getAllDevicesByOsName("Windows");

        // verify
        assertNotNull(dtoList);
        assert(dtoList.size()>1);
    }
    
    
}
