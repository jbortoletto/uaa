/*******************************************************************************
 *     Cloud Foundry 
 *     Copyright (c) [2009-2016] Pivotal Software, Inc. All Rights Reserved.
 *
 *     This product is licensed to you under the Apache License, Version 2.0 (the "License").
 *     You may not use this product except in compliance with the License.
 *
 *     This product includes a number of subcomponents with
 *     separate copyright notices and license terms. Your use of these
 *     subcomponents is subject to the terms and conditions of the
 *     subcomponent's license, as noted in the LICENSE file.
 *******************************************************************************/
package org.cloudfoundry.identity.uaa.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JSON serializer for Jackson to handle regular date instances as timestamps in
 * ISO format.
 * 
 * @author Dave Syer
 * 
 */
public class JsonDateSerializer extends JsonSerializer<Date> {

   
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = 
    	    new ThreadLocal<SimpleDateFormat>() {
    	        @Override
    	        protected SimpleDateFormat initialValue() {
    	            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    	        }
    	    };

    public static SimpleDateFormat getDateFormatter() {
        return DATE_FORMATTER.get();
    }
    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException,
        JsonProcessingException {
        String formatted = getDateFormatter().format(date);
        generator.writeString(formatted);
    }

}
