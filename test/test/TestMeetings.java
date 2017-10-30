package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestMeetings {

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
	public void testCrearMeetings() {

		String url = BASE_URL + "/meetings";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Peña");
		jsonObject.put("dateStart","2017-09-19,12:00");
		jsonObject.put("dateEnd","2017-09-19,14:00");
		jsonObject.put("idSite","21");
		jsonObject.put("idCalendar","24");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Morfi");
		jsonObject.put("dateStart","2017-09-19,15:00");
		jsonObject.put("dateEnd","2017-09-19,18:00");
		jsonObject.put("idSite","22");
		jsonObject.put("idCalendar","25");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Maraton");
		jsonObject.put("dateStart","2017-01-19,12:00");
		jsonObject.put("dateEnd","2017-01-19,14:00");
		jsonObject.put("idSite","21");
		jsonObject.put("idCalendar","25");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Futbol");
		jsonObject.put("dateStart","2017-02-19,12:00");
		jsonObject.put("dateEnd","2017-02-19,14:00");
		jsonObject.put("idSite","22");
		jsonObject.put("idCalendar","25");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Asalto");
		jsonObject.put("dateStart","2017-03-19,12:00");
		jsonObject.put("dateEnd","2017-03-19,14:00");
		jsonObject.put("idSite","21");
		jsonObject.put("idCalendar","27");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Inversiones");
		jsonObject.put("dateStart","2017-05-19,12:00");
		jsonObject.put("dateEnd","2017-05-19,14:00");
		jsonObject.put("idSite","22");
		jsonObject.put("idCalendar","29");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Pasantias");
		jsonObject.put("dateStart","2017-06-19,12:00");
		jsonObject.put("dateEnd","2017-06-19,14:00");
		jsonObject.put("idSite","21");
		jsonObject.put("idCalendar","30");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Cumpleaño");
		jsonObject.put("dateStart","2017-09-19,09:00");
		jsonObject.put("dateEnd","2017-09-19,10:00");
		jsonObject.put("idSite","22");
		jsonObject.put("idCalendar","24");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Aniversario");
		jsonObject.put("dateStart","2017-09-19,23:00");
		jsonObject.put("dateEnd","2017-09-19,07:00");
		jsonObject.put("idSite","21");
		jsonObject.put("idCalendar","30");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Navidad");
		jsonObject.put("dateStart","2017-12-19,09:00");
		jsonObject.put("dateEnd","2017-12-19,00:00");
		jsonObject.put("idSite","22");
		jsonObject.put("idCalendar","30");
		jsonObject.put("personal","0");
		jsonObject.put("remember","0");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);
	}

	@Test(dependsOnMethods= {"testCrearMeetings"})
	public void testGetMeeting() {

		String token =getToken();
		String url = BASE_URL + "/meetings/31";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearMeetings"})
	public void testGetMeetings() {

		String token =getToken();
		String url = BASE_URL + "/meetings";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearMeetings"})
	public void testUpdateMeeting() {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Nieto");
		jsonObject.put("dateStart","2017-09-19,12:00");
		jsonObject.put("dateEnd","2017-09-19,14:00");
		String jsonString = jsonObject.toString();

		String token =getToken();
		String url = BASE_URL + "/meetings/31";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token+"").type("application/json").put(ClientResponse.class,jsonString);

		System.out.println("\nPUT "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test(dependsOnMethods= {"testCrearMeetings"})
	public void testDeleteMeeting() {

		String token =getToken();
		String url = BASE_URL + "/meetings/32";
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

	@Test(dependsOnMethods= {"testCrearMeetings"})
	private void testGetMeetingsByUserAndDay() {

		String token =getToken();
		String url = BASE_URL + "/meetings/getMeetingsByUserAndDay?idUser=1&&date=2017-09-19,00:00";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearMeetings"})
	private void testGetMeetingsByUserBetweenDates() {

		String token =getToken();
		String url = BASE_URL + "/meetings/getMeetingsByUserBetweenDates?idUser=1&&date1=2017-09-19,00:00&&date2=2017-09-21,00:00";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearMeetings"})
	private void testGetOverlapMeetings() {

		String token =getToken();
		String url = BASE_URL + "/meetings/getOverlapMeetings?idUser=1&&date1=2017-09-18,12:00&&date2=2017-09-20,12:00";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}
}
