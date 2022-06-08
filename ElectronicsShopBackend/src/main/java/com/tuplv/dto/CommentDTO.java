package com.tuplv.dto;

public class CommentDTO extends AbstractDTO<CommentDTO> {
    private long userId;
    private String userAvatar;
    private String fullNameUser;
    private long productId;
    private String content;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}