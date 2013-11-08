package com.pearl.normandy.core.velocity;


import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.pearl.normandy.core.utils.ClassLoaderUtils;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DefaultVelocityManager implements VelocityManager
{
    private VelocityEngine ve;
    private static final Logger log = Logger.getLogger(DefaultVelocityManager.class);

    public DefaultVelocityManager()
    {
    }

    @SuppressWarnings("unchecked")
	public String getBody(String template, Map contextParameters) throws VelocityException
    {
        return getEncodedBody(template, null, null, contextParameters);
    }

    @SuppressWarnings("unchecked")
	public String getBody(String template, String baseurl, Map contextParameters) throws VelocityException
    {
        return getEncodedBody(template, baseurl, null, contextParameters);
    }

    @SuppressWarnings("unchecked")
	public String getEncodedBody(String template, String encoding, Map contextParameters) throws VelocityException
    {
        return getEncodedBody(template, null, encoding, contextParameters);
    }

    @SuppressWarnings("unchecked")
	public String getEncodedBody(String template, String baseurl, String encoding, Map contextParameters) throws VelocityException
    {
        return getEncodedBody(template, baseurl, encoding, createVelocityContext(createContextParams(baseurl, contextParameters)));
    }

    @SuppressWarnings("deprecation")
	public String getEncodedBody(String template, String baseurl, String encoding, Context context) throws VelocityException
    {
        //Check to see if the template exists
        if (template == null)
            throw new VelocityException("Trying to send mail with no template.");

        try
        {
            StringWriter writer = new StringWriter();

            if (encoding == null)
                getVe().mergeTemplate(template, context, writer);
            else
                getVe().mergeTemplate(template, encoding, context, writer);

            return writer.toString();
        }
        catch (ResourceNotFoundException e)
        {
            log.error("ResourceNotFoundException occurred whilst loading resource " + template);
            return "Could not locate resource " + template;

        }
        catch (MethodInvocationException mie)
        {
            Throwable e = mie.getWrappedThrowable();
            log.error("MethodInvocationException occurred getting message body from Velocity: " + e, e);
            return "An error occurred whilst rendering this message.  Please contact the administrators, and inform them of this bug.\n\nDetails:\n-------\n" + ExceptionUtils.getFullStackTrace(e);
        }
        catch (Exception e)
        {
            log.error("Exception getting message body from Velocity: " + e, e);
            return "An error occurred whilst rendering this message.  Please contact the administrators, and inform them of this bug.\n\nDetails:\n-------\n" + ExceptionUtils.getFullStackTrace(e);
        }
    }

    @SuppressWarnings("unchecked")
	protected Map createContextParams(String baseurl, Map contextParameters)
    {
        Map params = new HashMap();
        params.put("baseurl", baseurl);
        params.put("formatter", getDateFormat());
        params.putAll(contextParameters);
        return params;
    }

    @SuppressWarnings("unchecked")
	protected VelocityContext createVelocityContext(Map params)
    {
        if (params != null)
        {
            params.put("ctx", params);
        }

        return new VelocityContext(params)
        {
            //this overrides the functionality that doesn't allow null values to be set in the context
            public Object put(String key, Object value)
            {
                if (key == null)
                     return null;
                else
                    return internalPut(key, value);
            }
        };
    }

    @SuppressWarnings("unchecked")
	public String getEncodedBodyForContent(String content, String baseurl, Map contextParameters) throws VelocityException
    {
        //Check to see if the content is not null
        if (content == null)
            throw new VelocityException("Trying to send mail with no content.");

        try
        {
            VelocityContext context = createVelocityContext(createContextParams(baseurl, contextParameters));
            StringWriter writer = new StringWriter();

            getVe().evaluate(context, writer, "getEncodedBodyFromContent", content);

            return writer.toString();
        }
        catch (Exception e)
        {
            log.error("Exception getting message body from Velocity: " + e, e);
            return "An error occurred whilst rendering this message.  Please contact the administrators, and inform them of this bug.\n\nDetails:\n-------\n" + ExceptionUtils.getFullStackTrace(e);
        }
    }

    public DateFormat getDateFormat()
    {
        return new SimpleDateFormat("EEE, d MMM yyyy h:mm a");
    }

    protected synchronized VelocityEngine getVe()
    {
        if (ve == null)
        {
            ve = new VelocityEngine();
            initVe(ve);
        }

        return ve;
    }

    protected void initVe(VelocityEngine velocityEngine)
    {
       try
        {
            Properties props = new Properties();

            try
            {
                props.load(ClassLoaderUtils.getResourceAsStream("velocity.properties", getClass()));
                props.setProperty("file.resource.loader.path", System.getProperty("catalina.base")+props.getProperty("file.resource.loader.path"));
            }
            catch (Exception e)
            {
                //log.warn("Could not configure DefaultVelocityManager from velocity.properties, manually configuring.");
                props.put("resource.loader", "class");
                props.put("class.resource.loader.description", "Velocity Classpath Resource Loader");
                props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            }

            velocityEngine.init(props);
        }
        catch (Exception e)
        {
            log.error("Exception initialising Velocity: " + e, e);
        }
    }
}
