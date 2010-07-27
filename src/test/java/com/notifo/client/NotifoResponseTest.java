/**
 * Copyright (C) 2010 Sean P Scanlon.
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author sscanlon
 * 
 */
public class NotifoResponseTest {

    @Test
    public void testBasics() throws Exception {
        NotifoResponse response = new NotifoResponse();
        response.setResponseCode(200);
        response.setResponseMessage("foo");
        response.setStatus("success");
        assertTrue(response.isOk());
        assertEquals(response.getResponseCode(), 200);
        assertEquals(response.getStatus(), "success");
        assertEquals(response.getResponseMessage(), "foo");
        assertNotNull(response.toString());
    }

}
