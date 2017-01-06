package de.kisner.github.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.kisner.github.Bootstrap;

public class WebDavServer 
{
	final static Logger logger = LoggerFactory.getLogger(WebDavServer.class);
	
	public WebDavServer() throws Exception
	{
		Server server = new Server(8080);

		WebAppContext context = new WebAppContext();
		context.setDescriptor("WEB-INF/dav.xml");
		context.setResourceBase("./src/main/webapp");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);
		
		
		server.setHandler(context);
		server.start();
		server.join();
	}
	
	public static void main(String args[]) throws Exception
	{		
		Bootstrap.init();
		
		logger.info("Test with:");
		logger.info("\thttp://localhost:8080/app/index.jsf");
		
		new WebDavServer();
	}
}