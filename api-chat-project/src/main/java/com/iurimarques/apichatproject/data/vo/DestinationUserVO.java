package com.iurimarques.apichatproject.data.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DestinationUserVO implements Serializable {
    
    private long id;

    private String name;
    
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastInteraction;

    public DestinationUserVO() {}

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

    public void setLastInteractedWith(LocalDateTime lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    public LocalDateTime getLastInteraction() {
        return lastInteraction;
    }
}
