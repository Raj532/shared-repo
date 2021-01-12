package com.springboot.Soapdemo;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.springboot.Soapdemo.course.GetDataRequest;
import com.springboot.Soapdemo.course.GetDataResponse;

@Endpoint
public class WsEndpoint {
    
	@PayloadRoot(namespace="http://springboot.com/course" , localPart="GetDataRequest")
	@ResponsePayload
	public GetDataResponse getdata( @RequestPayload GetDataRequest request) {
		GetDataResponse getDataResponse = new GetDataResponse();
		getDataResponse.setEligible(true);
		return getDataResponse;
	}
}
