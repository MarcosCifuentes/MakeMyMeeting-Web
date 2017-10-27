package test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestToken {

	public final String BASE_URL="http://localhost:8080/MakeMyMeeting-Web/api";

	public Client client = Client.create();
	
	public static String token = null;

	@Test
	public void testUsuarioREST() throws IOException {
		
		getToken();
	}
	
	public void getToken()  throws IOException{

		String url = BASE_URL + "/autentication/";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("username","pepito");
		jsonObject.put("password","pepito123");
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		String responseContent= response.getEntity(String.class);
		System.out.println("Response Content : " + token);	
		this.token = responseContent;
	}
}
