package de.kisner.github.jetty.webdav;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.kisner.github.jetty.WebDavServer;

public class Product
{
	final static Logger logger = LoggerFactory.getLogger(WebDavServer.class);
	
	private String name;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product(String name)
    {
        this.name=name;
    }
}
