package de.kisner.github.jetty.rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplicationConfig extends Application
{
    private static final Set<Class<?>> CLASSES;

    static
    {
        HashSet<Class<?>> tmp = new HashSet<Class<?>>();
        tmp.add(SimpleRestService.class);
        CLASSES = Collections.unmodifiableSet(tmp);
    }

    @Override
    public Set<Class<?>> getClasses(){

       return  CLASSES;
    }    


}
