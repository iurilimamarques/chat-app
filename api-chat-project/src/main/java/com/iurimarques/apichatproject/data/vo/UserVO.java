package com.iurimarques.apichatproject.data.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserVO
 */
public class UserVO implements Serializable, Comparable<UserVO> {

    private long id;

    private String name;
    
    private String email;

    private String remoteAddress;

    private LocalDateTime lastInterection;

    public UserVO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public void setLastInterectedWith(LocalDateTime lastInterection) {
        this.lastInterection = lastInterection;
    }

    public LocalDateTime getLastInterection() {
        return lastInterection;
    }

    @Override
    public int compareTo(UserVO o) {
        return lastInterection.compareTo(o.getLastInterection());
    }
}