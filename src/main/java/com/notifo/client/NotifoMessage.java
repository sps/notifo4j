/**
 * Copyright (C) 2010 Sean P. Scanlon. 
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

/**
 * @author sscanlon
 * 
 */
public class NotifoMessage {

    private final String to;
    private final String message;
    private String subject;
    private String label;
    private String url;

    public NotifoMessage(String to, String message) {
        this.to = to;
        this.message = message;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *        the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     *        the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *        the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
