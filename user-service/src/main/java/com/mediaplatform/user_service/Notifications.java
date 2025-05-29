//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mediaplatform.user_service;

public class Notifications {
    private String notificationId;
    private String description;

    public Notifications() {
    }

    public Notifications(String notificationId, String description) {
        this.notificationId = notificationId;
        this.description = description;
    }

    public String getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
