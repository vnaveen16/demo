package com.demo.device.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.demo.device.dto.DeviceUserAgentDTO;
import com.demo.device.parser.DeviceDemoParser;
import com.demo.device.service.UserAgentService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/userAgent")
public class DeviceDemoController {
	
	@Autowired
	private DeviceDemoParser deviceDemoParser;
	
	@Autowired
	private UserAgentService userAgentService;
	
	
	@GetMapping("/createOrParse")
	public ResponseEntity<String> getUserAgentParsedDetails(HttpServletRequest request) {
		
		String  userAgent  =   request.getHeader("User-Agent");
		System.out.println(userAgent);
		DeviceUserAgentDTO userAgentDto=deviceDemoParser.getUserAgentParseDetails(userAgent);
		userAgentService.createOrMatchDevice(userAgentDto);
		
		return new ResponseEntity<>("successfull", HttpStatus.CREATED);
	}
	
	
	@GetMapping("/detailsById")
	public ResponseEntity<DeviceUserAgentDTO> getUserAgentDetailsById(@NonNull @RequestParam Long id) {
		
		DeviceUserAgentDTO deviceUserAgentDTO = userAgentService.getUserAgentDetailsById(id);
		return new ResponseEntity<>(deviceUserAgentDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("/allDevicesByOs")
	public ResponseEntity<List<DeviceUserAgentDTO>> getAllDevicesByOs(@NonNull @RequestParam String osName) {

		List<DeviceUserAgentDTO> deviceUserAgentDTOList = userAgentService.getAllDevicesByOsName(osName);
		return new ResponseEntity<>(deviceUserAgentDTOList, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteAllDevicesById")
	public ResponseEntity<String> deleteAllDevicesById(@NonNull @RequestParam String ids) {
		//ids.s
		List<Long> devicesIds= Stream.of(ids.split(","))
				.map(String::trim)
		        .map(Long::parseLong)
		        .collect(Collectors.toList());
		System.out.println(devicesIds);
		userAgentService.deleteAllDevicesById(devicesIds);
		return new ResponseEntity<>("successfull", HttpStatus.NO_CONTENT);
	}

}
