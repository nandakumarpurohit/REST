package com.demo.jaxrs.resteasy.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.demo.jaxrs.model.Employee;
import com.demo.jaxrs.model.GenericResponse;

public class RestEasyTestClient {
	
	static ResteasyClient client;
	
	RestEasyTestClient() {
		 client = new ResteasyClientBuilder().build();
	}

	public static void main(String[] args) {

		RestEasyTestClient obj = new RestEasyTestClient();
		
		//GET example
		/*ResteasyWebTarget getDummy = client.target("http://localhost:8089/RestEasy-Example/employee/99/getDummy");
		
		Response getDummyResponse = getDummy.request().get();
		
		String value = getDummyResponse.readEntity(String.class);
        System.out.println(value);
        getDummyResponse.close();  */
	
		obj.readData();
		
		//DELETE example
		ResteasyWebTarget delete = client.target("http://localhost:8089/RestEasy-Example/employee/50/delete");
		Response deleteResponse = delete.request().delete();
		System.out.println(deleteResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+deleteResponse.getStatus());
		deleteResponse.close();
		
		/*deleteResponse = delete.request().delete();
		System.out.println(deleteResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+deleteResponse.getStatus());
		deleteResponse.close();*/
		
		obj.readData();
		
		/*addResponse = add.request().post(Entity.entity(emp, MediaType.APPLICATION_XML));
		System.out.println(addResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+addResponse.getStatus());
		addResponse.close();*/
		
	}
	
	public void readData() {
		ResteasyWebTarget getData = client.target("http://localhost:8089/RestEasy-Example/employee/getAll");
		Response responseGetData = getData.request().get();
		String value = responseGetData.readEntity(String.class);
        System.out.println(value);
        responseGetData.close();
	}
	
	public void writeData() {
		//POST example
		ResteasyWebTarget add = client.target("http://localhost:8089/RestEasy-Example/employee/add");
		Employee emp = new Employee();
		emp.setId(50);emp.setName("Rocky");emp.setSalary(10000);
		Response addResponse = add.request().post(Entity.entity(emp, MediaType.APPLICATION_XML));
		System.out.println(addResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+addResponse.getStatus());
		addResponse.close();
		
		ResteasyWebTarget add2 = client.target("http://localhost:8089/RestEasy-Example/employee/add");
		Employee emp2 = new Employee();
		emp2.setId(75);emp2.setName("Ricky");emp2.setSalary(15000);
		Response addResponse2 = add2.request().post(Entity.entity(emp2, MediaType.APPLICATION_XML));
		System.out.println(addResponse2.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+addResponse2.getStatus());
		addResponse2.close();
	}

}
