package com.watermark.service.impl;

import com.watermark.dto.DocumentFactory;
import com.watermark.dto.Status;
import com.watermark.dto.api.BookRequest;
import com.watermark.dto.api.ContentEnum;
import com.watermark.dto.api.DocumentResponse;
import com.watermark.model.Document;
import com.watermark.model.Processing;
import com.watermark.repository.DocumentRepository;
import com.watermark.repository.ProcessingRepository;
import com.watermark.service.WatermarkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WatermarkServiceImplTest {


    private DocumentRepository documentRepository;
    private ProcessingRepository processingRepository;
    private DocumentFactory documentFactory;
    private ExecutorService executorService;

    private WatermarkService watermarkService;

    @BeforeEach
    void setUp() {
        documentRepository = Mockito.mock(DocumentRepository.class);
        processingRepository = Mockito.mock(ProcessingRepository.class);
        documentFactory = Mockito.mock(DocumentFactory.class);
        executorService = Mockito.mock(ExecutorService.class);

        watermarkService = new WatermarkServiceImpl(documentRepository, processingRepository, documentFactory);
    }

    @Test
    void createWatermark() {
        BookRequest documentRequest = new BookRequest(ContentEnum.book, "title", "author", "topic");
        watermarkService.createWatermark(documentRequest);

        Mockito.verify(processingRepository).save(any());
    }

    @Test
    void getPendingDocumentByTicketId() {
        final String ticketId = "mockTicketId";

        Mockito.when(processingRepository.findAllByTicketId(ticketId)).thenReturn(Optional.of(new Processing(ticketId, Status.PENDING)));

        DocumentResponse documentByTicketId = watermarkService.getDocumentByTicketId(ticketId);

        assertEquals(Status.PENDING, documentByTicketId.getStatus());
        assertEquals(ticketId, documentByTicketId.getTicketId());
    }

    @Test
    void getCompletedDocumentByTicketId() {
        final String ticketId = "mockTicketId";

        Mockito.when(processingRepository.findAllByTicketId(ticketId)).thenReturn(Optional.of(new Processing(ticketId, Status.COMPLETED)));
        Mockito.when(documentRepository.findAllByTicketId(ticketId)).thenReturn(Optional.of(new Document(ticketId, "content", "title", "author")));

        final DocumentResponse documentByTicketId = watermarkService.getDocumentByTicketId(ticketId);

        assertEquals(Status.COMPLETED, documentByTicketId.getStatus());
        assertEquals(ticketId, documentByTicketId.getTicketId());
    }
}