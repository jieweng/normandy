package com.pearl.normandy.core.velocity;

import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.context.Context;

import java.text.DateFormat;
import java.util.Map;

public interface VelocityManager
{
    String getBody(String template, Map contextParameters) throws VelocityException;

    String getBody(String template, String baseurl, Map contextParameters) throws VelocityException;

    String getEncodedBody(String template, String encoding, Map contextParameters) throws VelocityException;

    String getEncodedBody(String template, String baseurl, String encoding, Map contextParameters) throws VelocityException;

    String getEncodedBody(String template, String baseurl, String encoding, Context context) throws VelocityException;

    String getEncodedBodyForContent(String content, String baseurl, Map contextParameters) throws VelocityException;

    DateFormat getDateFormat();
}

