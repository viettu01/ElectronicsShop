package com.tuplv.electronicsshopapp.model;

public class Comment extends BaseModel {
    private long userId;
    private String fullNameUser;
    private String avatarUser;
    private String content;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }

    public String getAvatarUser() {
        return avatarUser;
    }

    public void setAvatarUser(String avatarUser) {
        this.avatarUser = avatarUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
