package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Test2daEntrega {

	public final String BASE_URL="http://localhost:8081/MakeMyMeeting-Web/api";

	public Client client = Client.create();


	@Test
	public void testCrearUsers() {

		String url = BASE_URL + "/users";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("userName","pepito");
		jsonObject.put("name","pablo");
		jsonObject.put("lastname","perez");
		jsonObject.put("email","pablo2017@gmail.com");
		jsonObject.put("password","pepito123");
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json")
				.post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test(dependsOnMethods= {"testCrearUsers"})
	public void testGetUsers() {

		String url = BASE_URL + "/users";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}
}
