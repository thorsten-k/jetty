package de.kisner.github.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.kisner.github.Bootstrap;
import de.kisner.github.jetty.servlet.HelloServlet;

public class JettyServer 
{
	final static Logger logger = LoggerFactory.getLogger(JettyServer.class);
	
	public JettyServer() throws Exception
	{
		Server server = new Server(8080);

		ResourceHandler fileHandler = new ResourceHandler();
		fileHandler.setResourceBase("./src/main/resources");
 
		ContextHandler ctx = new ContextHandler("/files");
		ctx.setHandler(fileHandler);
 
		WebAppContext context = new WebAppContext();
		context.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
		context.setResourceBase("./src/main/webapp");
		context.setContextPath("/app");
		context.setParentLoaderPriority(true);
		
		ServletContextHandler ctxServlet = new ServletContextHandler(ServletContextHandler.SESSIONS);
		ctxServlet.setContextPath("/servlet");
		ctxServlet.addServlet(new ServletHolder(new HelloServlet()),"/hello/*");
 
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] {context, ctx, ctxServlet});
		server.setHandler(handlers);

		server.start();
		server.join();
	}
	
	public static void main(String args[]) throws Exception
	{		
		Bootstrap.init();
		
		logger.info("Test with:");
		logger.info("\thttp://localhost:8080/app/index.jsf");
		logger.info("\thttp://localhost:8080/app/rest");
		logger.info("\thttp://localhost:8080/files/test.txt");
		logger.info("\thttp://localhost:8080/servlet/hello/x");
		
		new JettyServer();
	}
}