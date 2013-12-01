package com.example.findprice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


public class main {

	public static final String json = "{\"0\":{\"productname\":\"Nexus7 7\\\" 2GB 16GB Android Jellybean Tablet PC - Black\",\"imageurl\":\"http://www.pcrush.com/images/product/918562.gif\",\"producturl\":\"http://www.searchupc.com/rd.aspx?u=V971a0eHk9UpUxR1R2tKo3VfOMmYmRVTH50MnxCExT91uqUicf84jXQPpBAx17O7Rt6CS8BSjRLGsjTZEGcOjkpmPzMH%2f5nBaSl7S7b9S6Dko08aNZZKICz4AUjkUAuzKeSJsD0q8rytmVinKoh7BfMbf87fO5%2fN9WWxSv%2fcdx0eFKiOFeXuidWyoNp8hYKS4WX1Q7IhxW3ZsIblKnvXYtPQZpp9V1Haq2G52fNIHzA%3d\",\"price\":\"259.31\",\"currency\":\"USD\",\"saleprice\":\"\",\"storename\":\"pcRUSH.com\"},\"1\":{\"productname\":\"Google - Nexus 7\\\" Tablet with 16GB Memory (2nd Generation) - Black\",\"imageurl\":\"http://images.bestbuy.com/BestBuy_US/images/products/1484/1484847_54x108_s.gif\",\"producturl\":\"http://www.searchupc.com/rd.aspx?u=V971a0eHk9UpUxR1R2tKo3VfOMmYmRVTH50MnxCExT91uqUicf84jXQPpBAx17O7kn4DZ1FmB3L56OazwuHb%2bXS1hWI3kc16Wuot51cv8TZRPKPvNP9nWM7XQF8wiaXNVwP%2fNLsEcimTRvb7boFO2i9D3aZmqZmAi%2b3NBJr0kWWSLC3tAzfHywDK2aAnwd4Uert5ubBwKf4nxpwYCEZqRphuhjmC7gs9DITSKBNnWKGY4EVwQZ9IHrPW1ZhIcnSM49EDUZhUrtnokUbofS411MtCM0A7ww%2fxxh5VglwZdCdLma6Iher%2fe%2bslTH%2b73rLofja6ee7vWMy%2bGF1r9i4HyUHTF193yNBRPu31emc8KGsG3xQ9Br1OjbevR4pTjB1F\",\"price\":\"229.99\",\"currency\":\"USD\",\"saleprice\":\"\",\"storename\":\"bestbuy.com\"}}";
	private static ArrayList<DisplayProduct> list = new ArrayList<DisplayProduct>();
	
	public static void main(String[] args) {
		
		
		DisplayProduct dp = getItem(json);
		System.out.println(dp.getProductname());
		System.out.println(dp.getPrice());
		System.out.println(dp.getProducturl());
	}
	
	public static DisplayProduct getItem(String jsontext){
		Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		list.clear();
		try {
			node = mapper.readTree(jsontext);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<Entry<String, JsonNode>> fieldNames = node.fields();
		while (fieldNames.hasNext()) {
			Entry<String, JsonNode> fieldName = fieldNames.next();
			String genjson = fieldName.getValue().toString();
			//System.out.println(genjson);
			//System.out.println(fieldName);// prints title, message, errors,
			
			DisplayProduct dp = gson.fromJson(genjson, DisplayProduct.class);
			//System.out.println(dp.toString());
			
			if(dp.getCurrency() != null)
			{
				if(dp.getCurrency().equals("USD"))
					list.add(dp);
			}else{
				list.add(dp);
			}
		}
		
		DisplayProduct newproduct = list.get(0);
		
		for(DisplayProduct dp: list)
		{
			if(dp.getPrice() < newproduct.getPrice())
				newproduct = dp;
		}
		
		return newproduct;
	}

}
