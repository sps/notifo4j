/**
 * Copyright (C) 2010 Sean P. Scanlon 
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

/**
 * @author sscanlon
 * 
 */
public class NotifoHttpClient implements NotifoClient {

    private static final String SEND_NOTIFICATION_URL = "https://api.notifo.com/v1/send_notification";

    private static final String SUBSCRIBE_USER_URL = "https://api.notifo.com/v1/subscribe_user";

    private static final AuthScheme AUTH_SCHEME = new BasicScheme();

    private HttpClient httpClient = new DefaultHttpClient();

    private final UsernamePasswordCredentials credentials;

    private final String userName;

    /**
     * Construct a client with a username and apikey. "username" will be the default recipient of
     * messages
     * 
     * @param userName
     * @param apiKey
     */
    public NotifoHttpClient(String userName, String apiKey) {
        this.userName = userName;
        this.credentials = new UsernamePasswordCredentials(userName, apiKey);
        ((DefaultHttpClient) httpClient).addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(HttpRequest request, HttpContext context) throws HttpException,
                    IOException {
                request.addHeader(AUTH_SCHEME.authenticate(credentials, request));
            }
        });
    }

    @Override
    public NotifoResponse sendMessage(String messageBody) throws NotifoException {
        return sendMessage(new NotifoMessage(this.userName, messageBody));
    }

    @Override
    public NotifoResponse sendMessage(String to, String messageBody) throws NotifoException {
        return sendMessage(new NotifoMessage(to, messageBody));
    }

    /**
     * Send a message with the parameters in {@link NotifoMessage}
     */
    @Override
    public NotifoResponse sendMessage(NotifoMessage message) throws NotifoException {

        HttpPost post = new HttpPost(SEND_NOTIFICATION_URL);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("to", message.getTo()));
        nvps.add(new BasicNameValuePair("msg", message.getMessage()));

        addPairIfNotNull(nvps, "title", message.getSubject());
        addPairIfNotNull(nvps, "uri", message.getUrl());
        addPairIfNotNull(nvps, "label", message.getLabel());

        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new NotifoException(e.getMessage(), e.getCause());
        }

        try {
            HttpResponse response = httpClient.execute(post);

            return NotifoResponseFactory.parseResponse(response);

        } catch (Exception e) {
            throw new NotifoException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public NotifoResponse subscribeUser(String userName) throws NotifoException {
        HttpPost post = new HttpPost(SUBSCRIBE_USER_URL);
        try {
            post.setEntity(
                    new UrlEncodedFormEntity(
                            Arrays.asList(
                                    new BasicNameValuePair("username", userName)), HTTP.UTF_8)
                    );
            return NotifoResponseFactory.parseResponse(httpClient.execute(post));
        } catch (Exception e) {
            throw new NotifoException(e.getMessage(), e.getCause());
        }

    }

    private void addPairIfNotNull(List<NameValuePair> nvps, String key, Object value) {
        if (value != null) {
            nvps.add(new BasicNameValuePair(key, value.toString()));
        }

    }

    /**
     * Override the default http client
     * 
     * @param httpClient
     */
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

}
