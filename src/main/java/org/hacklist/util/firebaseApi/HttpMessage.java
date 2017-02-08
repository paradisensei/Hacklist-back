package org.hacklist.util.firebaseApi;

/**
 * @author Aidar Shaifutdinov.
 */
public class HttpMessage {

    private String to;

    private Notification notification;

    public HttpMessage(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

}
