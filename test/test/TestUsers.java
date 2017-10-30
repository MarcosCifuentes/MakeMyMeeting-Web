package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestUsers {

	public final String BASE_URL="http://localhost:8081/MakeMyMeeting-Web/api";

	public Client client = Client.create();


	public String getToken(){

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
		System.out.println("Response Content : " + responseContent);	
		return responseContent;

	}

	@Test
	public void testCrearUsers() {

		String url = BASE_URL + "/users";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("username","pepito");
		jsonObject.put("name","pablo");
		jsonObject.put("lastname","perez");
		jsonObject.put("email","pablo2017@gmail.com");
		jsonObject.put("password","pepito123");
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","MarceloRodriguez");
		jsonObject.put("name","Marcelo");
		jsonObject.put("lastname","Rodriguez");
		jsonObject.put("email","marcelo@gmail.com");
		jsonObject.put("password","Marcelo1");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","MateoDarin");
		jsonObject.put("name","Mateo");
		jsonObject.put("lastname","Darin");
		jsonObject.put("email","mateo@gmail.com");
		jsonObject.put("password","Mateo2");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","LionelMessi");
		jsonObject.put("name","Lionel");
		jsonObject.put("lastname","Messi");
		jsonObject.put("email","lionel@gmail.com");
		jsonObject.put("password","Lionel3");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","HectorSanchez");
		jsonObject.put("name","Hector");
		jsonObject.put("lastname","Sanchez");
		jsonObject.put("email","hector@gmail.com");
		jsonObject.put("password","Hector4");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","MartaLopez");
		jsonObject.put("name","Marta");
		jsonObject.put("lastname","Lopez");
		jsonObject.put("email","marta@gmail.com");
		jsonObject.put("password","Marta5");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","NataliaGomez");
		jsonObject.put("name","Natalia");
		jsonObject.put("lastname","Gomez");
		jsonObject.put("email","natalia@gmail.com");
		jsonObject.put("password","Natalia6");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","OscarNepes");
		jsonObject.put("name","Oscar");
		jsonObject.put("lastname","Nepes");
		jsonObject.put("email","oscar@gmail.com");
		jsonObject.put("password","Oscar7");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","SusanaGimenez"); 
		jsonObject.put("name","Susana");
		jsonObject.put("lastname","Gimenez");
		jsonObject.put("email","susana@gmail.com");
		jsonObject.put("password","Susana8");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("username","JulioIglesias");
		jsonObject.put("name","Julio");
		jsonObject.put("lastname","Iglesias");
		jsonObject.put("email","julio@gmail.com");
		jsonObject.put("password","Julio9");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test(dependsOnMethods= {"testCrearUsers"})
	public void testGetUser() {

		String token =getToken();
		String url = BASE_URL + "/users/1";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);
		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearUsers"})
	public void testGetUsers() {

		String token =getToken();
		String url = BASE_URL + "/users";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);
		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearUsers"})
	public void testUpdateUser() {



		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("username","pepito");
		jsonObject.put("name","peter");
		jsonObject.put("lastname","perez");
		jsonObject.put("email","pablo2017@gmail.com");
		jsonObject.put("password","pepito123");
		String jsonString = jsonObject.toString();

		String token =getToken();
		System.out.println(token);
		String url = BASE_URL + "/users/1";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token+"").type("application/json").put(ClientResponse.class,jsonString);

		System.out.println("\nPUT "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test(dependsOnMethods= {"testCrearUsers"})
	public void testDeleteUser() {

		String token =getToken();
		String url = BASE_URL + "/users/3";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).delete(ClientResponse.class);

		System.out.println("\nDELETE "+url);
		System.out.println("Response Code : " + response.getStatus());
		if(response.hasEntity())
			System.out.println("Response Content : " + response.getEntity(String.class));
		else
			System.out.println("Response Content : NO CONTENT");
		Assert.assertTrue(response.getStatus() == 200);

	}

}
