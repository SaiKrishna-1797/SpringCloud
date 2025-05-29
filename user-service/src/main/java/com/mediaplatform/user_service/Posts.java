//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mediaplatform.user_service;

public class Posts {


    private String postId;
    private String postDescription;

    public Posts() {
    }

    public Posts(String postId, String postDescription) {
        this.postId = postId;
        this.postDescription = postDescription;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostDescription() {
        return this.postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }
}
