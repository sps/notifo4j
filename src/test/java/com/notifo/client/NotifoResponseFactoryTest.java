/**
 * Copyright (C) 2010 Sean P Scanlon.
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sscanlon
 * 
 */
public class NotifoResponseFactoryTest {

    private HttpResponse mockResponse;
    private Mockery mockery;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        mockResponse = mockery.mock(HttpResponse.class);
        new NotifoResponseFactory(); // for code coverage :-)
    }

    @Test
    public void testParseResponse() throws Exception {
        mockery.checking(new Expectations() {
            {
                one(mockResponse).getEntity();
                will(returnValue(new StringEntity("{")));
            }
        });
        assertNull(NotifoResponseFactory.parseResponse(mockResponse));
        mockery.assertIsSatisfied();

        mockery.checking(new Expectations() {
            {
                one(mockResponse).getEntity();
                will(returnValue(new StringEntity("{}")));
            }
        });
        assertNotNull(NotifoResponseFactory.parseResponse(mockResponse));
        mockery.assertIsSatisfied();
    }
}
