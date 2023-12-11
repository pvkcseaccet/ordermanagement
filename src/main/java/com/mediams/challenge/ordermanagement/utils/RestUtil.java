package com.mediams.challenge.ordermanagement.utils;

import com.mediams.challenge.ordermanagement.rest.RestResponse;

public class RestUtil
{
	public static RestResponse buildSuccessResponse(String message, Object response)
	{
		return  RestResponse.Builder
			.aRestResponse()
			.withCode("success")
			.withMessage(message)
			.withData(response)
			.build();

	}

	public static RestResponse buildSuccessResponse(Object response)
	{
		return  RestResponse.Builder
			.aRestResponse()
			.withCode("success")
			.withMessage("Operation successfully done.")
			.withData(response)
			.build();

	}
}
