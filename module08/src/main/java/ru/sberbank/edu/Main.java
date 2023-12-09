package ru.sberbank.edu;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * Hello world!
 *
 */
public class Main
{
    public static final String CONTEXT = "/";
    public static final int PORT = 8080;

    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(PORT);

        StandardContext context = (StandardContext) tomcat.addWebapp(CONTEXT, new File("src/main/webapp/").getAbsolutePath());
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        context.setResources(resources);

        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}