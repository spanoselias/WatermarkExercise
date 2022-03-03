package com.watermark.controller;

import com.watermark.dto.Status;
import com.watermark.dto.api.DocumentRequest;
import com.watermark.dto.api.DocumentResponse;
import com.watermark.service.WatermarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Elias Spanos
 */
@RestController
@RequestMapping("/watermark")
public class WatermarkController {

    final WatermarkService watermarkService;

    public WatermarkController(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }

    @PostMapping()
    public ResponseEntity<DocumentResponse> createWatermark(
            @RequestBody DocumentRequest documentRequest) {

        final String ticketId = watermarkService.createWatermark(documentRequest);

        return ResponseEntity.ok(new DocumentResponse(ticketId, Status.PENDING));
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<DocumentResponse> getDocument(
            @PathVariable String ticketId) {

        final DocumentResponse documentResponse =
                watermarkService.getDocumentByTicketId(ticketId);

        return ResponseEntity.ok(documentResponse);
    }
}
