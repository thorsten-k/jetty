package de.kisner.github.jetty.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/rest")
@Produces("text/plain")
public class SimpleRestService
{
	@GET
	public String hello()
	{
		return "Hello World";
	}

	@GET @Path("/books")
	public String getBooks()
	{
		return "books";
	}

	@GET @Path("/book/{isbn}")
	public String getBook(@PathParam("isbn") String id)
	{
		return id;
   }
}