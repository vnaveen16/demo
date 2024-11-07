package com.demo.device.controller;



import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import com.demo.device.repository.UserAgentRepository;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.demo.device.dto.DeviceUserAgentDTO;
import com.demo.device.entity.UserAgentEntity;
import com.demo.device.service.UserAgentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(DeviceDemoController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeviceUserAgentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAgentService userAgentService;


    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired HttpServletRequest request;

    @MockBean
    private UserAgentRepository userAgentRepository;

    com.demo.device.dto.DeviceUserAgentDTO deviceUserAgentDTO;
    com.demo.device.entity.UserAgentEntity userAgentEntity;

    @BeforeEach
    public void setup(){
    	userAgentEntity = UserAgentEntity.builder()
                .deviceId(352L)
                .osNameValue("Windows")
                .osVersion("10.0")
                .browserName("Chrome")
                .browserVersion("130.0.0.0")
                //.hitCount(7)
                .build();
    	deviceUserAgentDTO = DeviceUserAgentDTO.builder()
                .deviceId(352L)
                .osNameValue("Windows")
                .osVersion("10.0")
                .browserName("Chrome")
                .browserVersion("130.0.0.0")
                //.hitCount(7)
                .build();

    }
    
    @Test
    @Order(1)
    public void testCreateUserAgentRecord() throws Exception{
        // precondition
    	//((UserAgentService) given(userAgentService)).createOrMatchDevice(deviceUserAgentDTO).willReturn(userAgentEntity);
    	given(userAgentService.createOrMatchDevice(deviceUserAgentDTO)).willReturn(userAgentEntity);
        
        // action
    	ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/userAgent/createOrParse", request));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
        // verify
       
    }
    @Test
    @Order(2)
    public void testGetDeviceUserAgentByDeviceId() throws Exception{
        //given(userAgentService).createOrMatchDevice(deviceUserAgentDTO);
        given(userAgentService.getUserAgentDetailsById(deviceUserAgentDTO.getDeviceId())).willReturn(deviceUserAgentDTO);
        // Action and Verify
        mockMvc.perform(MockMvcRequestBuilders.get("/userAgent/detailsById").param("id", String.valueOf(deviceUserAgentDTO.getDeviceId())))

                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.osNameValue", is(deviceUserAgentDTO.getOsNameValue())))
                .andExpect(jsonPath("$.osVersion", is(deviceUserAgentDTO.getOsVersion())))
                .andExpect(jsonPath("$.browserName", is(deviceUserAgentDTO.getBrowserName())))
                .andExpect(jsonPath("$.browserVersion", is(deviceUserAgentDTO.getBrowserVersion())));


    }



    //Get All employees Test
    @Test
    @Order(3)
    public void testGetAllDeviceUserAgentsByOsName() throws Exception {
        // precondition
        List<DeviceUserAgentDTO> deviceList = new ArrayList<>();
        deviceList.add(deviceUserAgentDTO);
        deviceList.add(DeviceUserAgentDTO.builder().deviceId(353L).browserName("Firefox").browserVersion("132.0").osNameValue("Windows").osVersion("10.0").build());

        given(userAgentService.getAllDevicesByOsName(deviceUserAgentDTO.getOsNameValue())).willReturn(deviceList);

        // Action and Verify
        mockMvc.perform(MockMvcRequestBuilders.get("/userAgent/allDevicesByOs").param("osName", "Windows"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(deviceList.size())));

    }

    //Delete employees Test
    @Test
    @Order(4)
    public void testDeleteDevicebyIds() throws Exception{
        willDoNothing().given(userAgentService).deleteAllDevicesById(Arrays.asList(353L, 353L,353L));
        // Action and Verify
        mockMvc.perform(MockMvcRequestBuilders.delete("/userAgent/deleteAllDevicesById").param("ids", "353, 353,353"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
