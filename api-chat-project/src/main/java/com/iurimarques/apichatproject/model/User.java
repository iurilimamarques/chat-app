package com.iurimarques.apichatproject.model;

import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;

@JsonIgnoreProperties({"messagesAsSender", "messagesAsRecipient"})
@Entity
@Table(name = "users")
public class User implements Serializable, Principal {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    private Set<Message> messagesAsSender;

    @OneToMany(mappedBy = "userDestination", fetch = FetchType.LAZY)
    private Set<Message> messagesAsRecipient;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Message> getMessagesAsRecipient() {
        return messagesAsRecipient;
    }

    public void setMessagesRecipient(Set<Message> messagesAsRecipient) {
        this.messagesAsRecipient = messagesAsRecipient;
    }

    public Set<Message> getMessagesAsSender() {
        return messagesAsSender;
    }

}