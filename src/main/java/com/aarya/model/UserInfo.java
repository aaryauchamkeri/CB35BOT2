package com.aarya.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInfo {

    private String id;
    private String username;
    private String avatar;
    private String discriminator;
    private String public_flags;
    private String flags;
    private String locale;
    private String mfa_enabled;
    private String email;
    private boolean verified;

    public UserInfo(String id, String username, String avatar, String discriminator, String public_flags, String flags, String locale, String mfa_enabled, String email, boolean verified) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.discriminator = discriminator;
        this.public_flags = public_flags;
        this.flags = flags;
        this.locale = locale;
        this.mfa_enabled = mfa_enabled;
        this.email = email;
        this.verified = verified;
    }

    public UserInfo(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getPublic_flags() {
        return public_flags;
    }

    public void setPublic_flags(String public_flags) {
        this.public_flags = public_flags;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getMfa_enabled() {
        return mfa_enabled;
    }

    public void setMfa_enabled(String mfa_enabled) {
        this.mfa_enabled = mfa_enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString(){
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch(JsonProcessingException e){
            return "Processing error occurred";
        }
    }
}
