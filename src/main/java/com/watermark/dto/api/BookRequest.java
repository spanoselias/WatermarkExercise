package com.watermark.dto.api;

public class BookRequest extends DocumentRequest {

    private String topic;

    public BookRequest(){}

    public BookRequest(String topic) {
        this.topic = topic;
    }

    public BookRequest(ContentEnum content, String title, String author, String topic) {
        super(content, title, author);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

}
