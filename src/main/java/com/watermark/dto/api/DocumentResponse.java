package com.watermark.dto.api;

import com.watermark.dto.Status;

public final class DocumentResponse {
    private final String ticketId;

    private final Status status;

    private String content;
    private String title;
    private String author;
    private String topic;

    public DocumentResponse(String submittedId,
                            Status status,
                            String content,
                            String title,
                            String author,
                            String topic) {
        this.ticketId = submittedId;
        this.status = status;
        this.content = content;
        this.title = title;
        this.author = author;
        this.topic = topic;
    }

    public DocumentResponse(String submittedId,
                            Status status) {
        this.ticketId = submittedId;
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Status getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTopic() {
        return topic;
    }
}
