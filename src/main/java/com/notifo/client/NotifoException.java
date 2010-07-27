/**
 * Copyright (C) 2010 Sean P. Scanlon. 
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

/**
 * @author sscanlon
 * 
 */
public class NotifoException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotifoException(String message, Throwable cause) {
        super(message, cause);
    }
}
