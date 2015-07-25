package de.kisner.github.jetty;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import de.kisner.github.Bootstrap;
import de.kisner.github.jetty.rest.SimpleRestService;

public class NettyServer 
{
	public NettyServer() throws Exception
	{
		ResteasyDeployment deployment = new ResteasyDeployment();
    	deployment.getActualResourceClasses().add(SimpleRestService.class);
    	
    	NettyJaxrsServer netty = new NettyJaxrsServer();
    	netty.setDeployment(deployment);
    	netty.setPort(8080);
    	netty.setRootResourcePath("");
    	netty.setSecurityDomain(null);
    	netty.start();
	}
	
	public static void main(String args[]) throws Exception
	{		
		Bootstrap.init();
		new NettyServer();
	}
}