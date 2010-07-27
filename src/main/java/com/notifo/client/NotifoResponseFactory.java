/**
 * Copyright (C) 2010 Sean P Scanlon.
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * @author sscanlon
 * 
 */
public class NotifoResponseFactory {

    public static final Gson GSON = new Gson();

    public static NotifoResponse parseResponse(HttpResponse response) {
        NotifoResponse toReturn = null;
        try {
            String body = EntityUtils.toString(response.getEntity());
            toReturn = GSON.fromJson(body, NotifoResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toReturn;
    }

}
