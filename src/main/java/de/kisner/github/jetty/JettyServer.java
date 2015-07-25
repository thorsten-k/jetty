package de.kisner.github.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import de.kisner.github.Bootstrap;

public class JettyServer 
{
	public JettyServer() throws Exception
	{
		Server server = new Server(8080);

		ResourceHandler fileHandler = new ResourceHandler();
		fileHandler.setResourceBase("./src");
 
		ContextHandler ctx = new ContextHandler("/files"); /* the server uri path */
		ctx.setHandler(fileHandler);
 
		WebAppContext context = new WebAppContext();
		context.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
		context.setResourceBase("./src/main/webapp");
		context.setContextPath("/cast");
		context.setParentLoaderPriority(true);            
 
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { context, ctx});
		server.setHandler(handlers);

		server.start();
		server.join();
	}
	
	public static void main(String args[]) throws Exception
	{		
		Bootstrap.init();
		new JettyServer();
	}
}