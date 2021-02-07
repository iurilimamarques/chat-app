package com.iurimarques.apichatproject.data.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageVO implements Serializable {
    
    private Long id;

    private String message;

    @JsonFormat(pattern="HH:mm dd/MM/yyyy")
    private LocalDateTime createdAt;

    private UserMessageVO fromUser;

    private UserMessageVO userDestination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserMessageVO getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserMessageVO fromUser) {
        this.fromUser = fromUser;
    }

    public UserMessageVO getUserDestination() {
        return userDestination;
    }

    public void setUserDestination(UserMessageVO userDestination) {
        this.userDestination = userDestination;
    }
}
