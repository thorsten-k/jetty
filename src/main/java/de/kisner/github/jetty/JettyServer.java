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
import de.kisner.github.jetty.security.SecurityHandlerFactory;
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
		
		ServletContextHandler ctxServletPublic = new ServletContextHandler(ServletContextHandler.SESSIONS);
		ctxServletPublic.setContextPath("/servlet/public");
		ctxServletPublic.addServlet(new ServletHolder(new HelloServlet()),"/hello/*");
		
		ServletContextHandler ctxServletSecured = new ServletContextHandler(ServletContextHandler.SESSIONS);
		ctxServletSecured.setContextPath("/servlet/secured");
		ctxServletSecured.addServlet(new ServletHolder(new HelloServlet()),"/hello/*");
		ctxServletSecured.setSecurityHandler(SecurityHandlerFactory.basicAuth("scott", "tiger2", "Private!"));
		
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] {context, ctx, ctxServletPublic, ctxServletSecured});
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
		logger.info("\thttp://localhost:8080/servlet/public/hello/x");
		logger.info("\thttp://localhost:8080/servlet/secured/hello/x");
		
		new JettyServer();
	}
}