package org.hacklist.task;

import org.hacklist.util.firebaseApi.HttpMessage;
import org.hacklist.util.firebaseApi.Notification;
import org.hacklist.util.socialApi.props.Firebase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aidar Shaifutdinov.
 *
 * This task is responsible for sending push notifications.
 *
 * note: for now it sends push notifications to anyone
 *       subscribed to 'news' topic
 */
@Component
public class SendPushNotificationsTask {

    private final Firebase firebase;
    private final RestTemplate restTemplate;

    @Autowired
    public SendPushNotificationsTask(Firebase firebase, RestTemplate restTemplate) {
        this.firebase = firebase;
        this.restTemplate = restTemplate;
    }

    // fixedRate = 1 week
    @Scheduled(fixedRate = 604800000)
    public void sendPushNotifications() {
        // headers
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "key=" + firebase.getKey());
        headers.add("Content-Type", "application/json");
        // body
        Notification notification = new Notification("News", "New hacks!");
        HttpMessage message = new HttpMessage("/topics/news", notification);

        HttpEntity<HttpMessage> httpEntity = new HttpEntity<>(message, headers);
        restTemplate.postForObject(firebase.getUrl(), httpEntity, Object.class);
    }

}
