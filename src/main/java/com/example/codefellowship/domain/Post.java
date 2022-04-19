package com.example.codefellowship.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    @CreationTimestamp
    private Timestamp creditAt;

    public Post(String body) {
        this.body = body;
        this.creditAt = creditAt;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreditAt() {
        return creditAt;
    }

    public void setCreditAt(Timestamp creditAt) {
        this.creditAt = creditAt;
    }
}
