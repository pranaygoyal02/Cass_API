package com.rest.cassandra.operations;

/* Pranay Goyal 
 * 
 * The Controller does two operations
 * The view path  queries the product table 
 * The load path stores a image into a BLOB data type */



import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rest.cassandra.operations.CassMethod;



@Path("/images")
public class CassController  extends CassMethod{

	@GET
	@Path("/view")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewprod(@QueryParam("prod_name") String prod_name)
	{
		//This url hits the class CassMethod with the viewProd method.
		Response rb = null;
		System.out.println("I am inside the viewProductionDatabase");
	    JSONArray j=new JSONArray();
		j=new CassMethod().viewProd(prod_name);
		rb=Response.ok(j.toString()).build();
		return rb;
	}
	
	@GET
	@Path("/load")
	@Produces(MediaType.APPLICATION_JSON)
	public Response imageload(@QueryParam("file") String filename)
	{
		
		//This method demonstrates loading the blob data into the database
		new CassImageLoader().imageLoader(filename);
		return Response.ok("The image row has been added to the Message table").build();
	}
	

} 