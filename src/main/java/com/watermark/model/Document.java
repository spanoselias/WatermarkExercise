package com.watermark.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue
    private Long id;

    private String ticketId;
    private String content;
    private String title;
    private String author;
    private String topic;

    public Document() {}

    public Document(String ticketId, String content, String title, String author, String topic) {
        this.ticketId = ticketId;
        this.content = content;
        this.title = title;
        this.author = author;
        this.topic = topic;
    }

    public Document(String ticketId, String content, String title, String author) {
        this.ticketId = ticketId;
        this.content = content;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
