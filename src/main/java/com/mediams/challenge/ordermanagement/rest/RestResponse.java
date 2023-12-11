package com.mediams.challenge.ordermanagement.rest;

public class RestResponse
{
	private String code;

	private String message;

	private String entityName;

	private Object data;

	public String getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}

	public Object getData()
	{
		return data;
	}

	public static final class Builder
	{
		private String code;
		private String message;
		private Object data;

		private Builder()
		{
		}

		public static Builder aRestResponse()
		{
			return new Builder();
		}

		public Builder withCode(String code)
		{
			this.code = code;
			return this;
		}

		public Builder withMessage(String message)
		{
			this.message = message;
			return this;
		}

		public Builder withData(Object data)
		{
			this.data = data;
			return this;
		}

		public RestResponse build()
		{
			RestResponse restResponse = new RestResponse();
			restResponse.data = this.data;
			restResponse.code = this.code;
			restResponse.message = this.message;
			return restResponse;
		}
	}
}
