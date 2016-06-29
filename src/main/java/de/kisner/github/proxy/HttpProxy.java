package de.kisner.github.proxy;

import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.kisner.github.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

public class HttpProxy 
{
	final static Logger logger = LoggerFactory.getLogger(HttpProxy.class);
	
	public static void main(String args[]) throws Exception
	{		
		Bootstrap.init();
		
		HttpProxyServer server =
			    DefaultHttpProxyServer.bootstrap()
			        .withPort(8080)
			        .withFiltersSource(new HttpFiltersSourceAdapter() {
			            public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
			                return new HttpFiltersAdapter(originalRequest) {
			                    @Override
			                    public HttpResponse clientToProxyRequest(HttpObject http)
			                    {
			                       logger.info("cl "+http.toString());
			                       return null;
			                    }

			                    @Override
			                    public HttpObject serverToProxyResponse(HttpObject httpObject)
			                    {
			                    	 logger.info("se");
			                        return httpObject;
			                    }
			                };
			            }
			        })
			        .start();
	}
}