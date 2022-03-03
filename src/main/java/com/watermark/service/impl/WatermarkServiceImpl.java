package com.watermark.service.impl;

import com.watermark.dto.*;
import com.watermark.dto.api.DocumentRequest;
import com.watermark.dto.api.DocumentResponse;
import com.watermark.exception.InvalidTicketIdException;
import com.watermark.model.Document;
import com.watermark.model.Processing;
import com.watermark.repository.DocumentRepository;
import com.watermark.repository.ProcessingRepository;
import com.watermark.service.WatermarkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WatermarkServiceImpl implements WatermarkService {

    public static final int MAX_AVAILABLE_THREADS = 30;
    final ExecutorService executor =
            Executors.newFixedThreadPool(MAX_AVAILABLE_THREADS);

    final DocumentRepository documentRepository;
    final ProcessingRepository processingRepository;
    final DocumentFactory documentFactory;

    public WatermarkServiceImpl(final DocumentRepository documentRepository,
                                final ProcessingRepository processingRepository,
                                final DocumentFactory documentFactory) {
        this.documentRepository = documentRepository;
        this.processingRepository = processingRepository;
        this.documentFactory = documentFactory;
    }

    @Override
    @Transactional
    public String createWatermark(DocumentRequest documentRequest) {
        final String ticketId = UUID.randomUUID().toString();

        processingRepository.save(new Processing(ticketId, Status.PENDING));

        executor.submit(() -> constructWatermark(ticketId, documentRequest));

        return ticketId;
    }

    @Override
    public DocumentResponse getDocumentByTicketId(String ticketId) {

        final Processing processingStatusByTicketId =
                processingRepository.findAllByTicketId(ticketId)
                        .orElseThrow(() -> new InvalidTicketIdException(ticketId));

        if (processingStatusByTicketId.getStatus() == Status.COMPLETED) {
            final Document allByTicketId =
                    documentRepository.findAllByTicketId(ticketId)
                            .orElseThrow(() -> new InvalidTicketIdException(ticketId));

            return buildDocumentResponse(ticketId, allByTicketId);
        }

        return buildPendingDocumentResponse(ticketId);
    }

    private DocumentResponse buildDocumentResponse(final String id, Document document) {
        return new DocumentResponse(
                id,
                Status.COMPLETED,
                document.getContent(),
                document.getTitle(),
                document.getAuthor(),
                document.getTopic());
    }

    private DocumentResponse buildPendingDocumentResponse(final String id) {
        return new DocumentResponse(
                id,
                Status.PENDING);
    }

    private void constructWatermark(final String ticketId, DocumentRequest documentRequest) {

        // simulate the delay needed for creating watermark
        documentRepository.save(documentFactory.create(ticketId, documentRequest));

        final Processing fetchedCurrentProcessing =
                processingRepository.findAllByTicketId(ticketId)
                        .orElseThrow(() -> new InvalidTicketIdException(ticketId));

        fetchedCurrentProcessing.setStatus(Status.COMPLETED);
        processingRepository.save(fetchedCurrentProcessing);
    }
}
