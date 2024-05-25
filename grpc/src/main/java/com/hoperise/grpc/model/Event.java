package com.hoperise.grpc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "system_events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "microservice_name")
    private String microserviceName;

    @Column(name = "user")
    private String user;

    @Column(name = "action")
    private String action;

    @Column(name = "resource")
    private String resource;

    @Column(name = "response_type")
    private String responseType;

    public Event() {
    }

    public Event(String timestamp, String microserviceName, String user, String action, String resource, String responseType) {
        this.timestamp = timestamp;
        this.microserviceName = microserviceName;
        this.user = user;
        this.action = action;
        this.resource = resource;
        this.responseType = responseType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMicroserviceName() {
        return microserviceName;
    }

    public void setMicroserviceName(String microserviceName) {
        this.microserviceName = microserviceName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}