package com.watermark.service;

import com.watermark.dto.api.DocumentRequest;
import com.watermark.dto.api.DocumentResponse;

import java.util.Optional;


public interface WatermarkService {

    String createWatermark(final DocumentRequest documentRequest);

    DocumentResponse getDocumentByTicketId(final String ticketId);
}
