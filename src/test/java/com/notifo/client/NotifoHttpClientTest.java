/**
 * Copyright (C) 2010 Sean P. Scanlon.
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sscanlon
 * 
 */
public class NotifoHttpClientTest {

    private Mockery mockery;

    private NotifoHttpClient client;
    private HttpClient httpClient;
    private HttpResponse mockResponse;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        httpClient = mockery.mock(HttpClient.class);
        mockResponse = mockery.mock(HttpResponse.class);
        client = new NotifoHttpClient("username", "apikey");
        client.setHttpClient(httpClient);
    }

    @Test(expected = NotifoException.class)
    public void testSendMessageWithException() throws Exception {

        mockery.checking(new Expectations() {
            {
                one(httpClient).execute(with(any(HttpPost.class)));
                will(throwException(new IOException("oops")));
            }
        });

        client.sendMessage("test");

        fail("shouldn't get here");
    }

    @Test
    public void testSendMessage() throws Exception {

        mockery.checking(new Expectations() {
            {
                exactly(3).of(httpClient).execute(with(any(HttpPost.class)));
                will(returnValue(mockResponse));
                exactly(3).of(mockResponse).getEntity();
                will(returnValue(
                        new StringEntity("{}", "UTF-8")));
                    }
        });

        client.sendMessage("test");
        client.sendMessage("user", "test");

        NotifoMessage m = new NotifoMessage("", "");
        m.setLabel("test label");
        m.setSubject(null);
        m.setUrl(null);
        client.sendMessage(m);

        mockery.assertIsSatisfied();
    }

    @Test
    public void testSubscribeUser() throws Exception {
        mockery.checking(new Expectations() {
            {
                one(httpClient).execute(with(any(HttpPost.class)));
                will(returnValue(mockResponse));
                one(mockResponse).getEntity();
                will(returnValue(new StringEntity("{}", "UTF-8")));
            }
        });
        assertNotNull(client.subscribeUser("sps"));
        mockery.assertIsSatisfied();
    }
}
