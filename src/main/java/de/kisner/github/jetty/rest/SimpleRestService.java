package de.kisner.github.jetty.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/rest")
public class SimpleRestService
{
	@GET @Produces("text/plain")
	public String hello()
	{
		return "Hello World";
	}

	@GET @Path("/books") @Produces("text/plain")
	public String getBooks()
	{
		return "books";
	}

	@GET @Path("/book/{isbn}") @Produces("text/plain")
	public String getBook(@PathParam("isbn") String id)
	{
		return id;
   }
}