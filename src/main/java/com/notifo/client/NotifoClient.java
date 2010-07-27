/**
 * Copyright (C) 2010 Sean P. Scanlon. 
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

/**
 * @author sscanlon
 * 
 */
public interface NotifoClient {

    /**
     * 
     * @param messageBody
     * @return
     * @throws NotifoException
     */
    NotifoResponse sendMessage(NotifoMessage message) throws NotifoException;

    /**
     * Send a message to a particular user
     */
    NotifoResponse sendMessage(String to, String messageBody) throws NotifoException;

    /**
     * Send a message to the default user (you)
     */
    NotifoResponse sendMessage(String messageBody) throws NotifoException;

    /**
     * 
     * @param userName
     * @return
     * @throws NotifoException
     */
    NotifoResponse subscribeUser(String userName) throws NotifoException;

}
