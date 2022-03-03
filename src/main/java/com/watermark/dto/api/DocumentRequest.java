package com.watermark.dto.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Compatible with swagger 3. In swagger 3 it will show two different
// requests.
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "content", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BookRequest.class, name = "book"),
        @JsonSubTypes.Type(value = JournalRequest.class, name = "journal"),
})
public abstract class DocumentRequest {

    private ContentEnum content;
    private String title;
    private String author;

    public DocumentRequest() {}

    public DocumentRequest(ContentEnum content, String title, String author) {
        this.content = content;
        this.title = title;
        this.author = author;
    }

    public ContentEnum getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
