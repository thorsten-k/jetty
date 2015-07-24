package de.kisner.github;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.LoggerInit;

public class Bootstrap
{
	final static Logger logger = LoggerFactory.getLogger(Bootstrap.class);
	public final static String xmlConfig = "config.jetty/jetty.xml";
	
	public static Configuration init()
	{
		return init(xmlConfig);
	}
	
	public static Configuration init(String configFile)
	{
		LoggerInit loggerInit = new LoggerInit("log4j.xml");
			loggerInit.addAltPath("config.jetty");
			loggerInit.init();
			
		return null;
	}
}