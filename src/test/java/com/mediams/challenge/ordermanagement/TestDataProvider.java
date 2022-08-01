package com.mediams.challenge.ordermanagement;

public class TestDataProvider
{
	public static final String ORDER_CREATION_PAYLOAD1 = "{"
		+ "    \"total\": \"10.68\","
		+ "    \"line_items\" : ["
		+ "        {\"item_id\": 3, \"quantity\": 2, \"total\":\"10.68\"}"
		+ "    ]"
		+ "}";

	public static final String ORDER_CREATION_PAYLOAD_WITH_EMPTY_ITEMS = "{"
		+ "    \"total\": \"10.68\" " + "}";

}
