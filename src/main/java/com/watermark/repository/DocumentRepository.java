package com.watermark.repository;

import com.watermark.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, String> {

    Optional<Document> findAllByTicketId(final String ticketId);
}
