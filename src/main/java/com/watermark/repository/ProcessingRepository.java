package com.watermark.repository;

import com.watermark.model.Document;
import com.watermark.model.Processing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessingRepository extends JpaRepository<Processing, Long> {

    Optional<Processing> findAllByTicketId(final String ticketId);
}
