package com.watermark.dto.api;

public class JournalRequest extends DocumentRequest {
    public JournalRequest() {
    }

    public JournalRequest(ContentEnum content, String title, String author) {
        super(content, title, author);
    }
}
