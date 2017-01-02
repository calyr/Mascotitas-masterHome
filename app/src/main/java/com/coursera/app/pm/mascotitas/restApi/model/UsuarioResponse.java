package com.coursera.app.pm.mascotitas.restApi.model;

/**
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class UsuarioResponse {
    private String id;
    private String token;
    private String userInstagram;
    private String urlImage;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUserInstagram() {
        return userInstagram;
    }

    public void setUserInstagram(String userInstagram) {
        this.userInstagram = userInstagram;
    }

    public UsuarioResponse(String id, String token, String userInstagram) {
        this.id = id;
        this.token = token;
        this.userInstagram = userInstagram;
        this.urlImage = null    ;
        this.fullName = "";

    }

    public UsuarioResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
