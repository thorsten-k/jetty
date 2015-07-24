package de.kisner.github.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import de.kisner.github.Bootstrap;

public class NettyServer 
{
	public NettyServer() throws Exception
	{
		 Server server = new Server(8080);

         WebAppContext context = new WebAppContext();

         context.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
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
		new NettyServer();
	}
}