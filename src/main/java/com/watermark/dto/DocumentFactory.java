package com.watermark.dto;

import com.watermark.dto.api.BookRequest;
import com.watermark.dto.api.DocumentRequest;
import com.watermark.dto.api.JournalRequest;
import com.watermark.model.Document;
import org.springframework.stereotype.Service;


@Service
public class DocumentFactory {
    public Document create(final String ticketId, final DocumentRequest documentRequest) {
        if (documentRequest instanceof BookRequest) {
            final BookRequest bookRequest = (BookRequest) documentRequest;
            return
                    new Document(
                            ticketId,
                            bookRequest.getContent().toString(),
                            bookRequest.getTitle(),
                            bookRequest.getAuthor(),
                            bookRequest.getTopic());
        } else {
            final JournalRequest journalRequest = (JournalRequest) documentRequest;

            return
                    new Document(
                            ticketId,
                            journalRequest.getContent().toString(),
                            journalRequest.getTitle(),
                            journalRequest.getAuthor());
        }
    }

}
