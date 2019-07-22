package com.demo.enterprise.rest.resteasy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;



@Path("/jsonServices")
public class RESTEasyJSONServices {

	@GET
	@Path("/print/{name}")
	@Produces("application/json")
	public Student produceJSON( @PathParam("name") String name ) {
		
		Student st = new Student(name, "Marco",19,12);

		return st;

	}
	
	@POST
	@Path("/send")
	@Consumes("application/json")
	public Response consumeJSON( Student student ) {
		
		String output = student.toString();

		return Response.status(200).entity(output).build();
	}


}
