package com.watermark.repository;

import com.watermark.dto.Status;
import com.watermark.model.Processing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProcessingRepositoryTest {

    @Autowired
    private ProcessingRepository processingRepository;

    @BeforeEach
    void setUp() {
        processingRepository.deleteAllInBatch();
    }

    @Test
    void findAllByTicketId() {

        final String mockTicketId = "mockTicketId";

        final Processing mockProcessing = new Processing(mockTicketId, Status.PENDING);
        processingRepository.save(mockProcessing);

        final Optional<Processing> fetchedProcessingByTicketId =
                processingRepository.findAllByTicketId(mockTicketId);

        assertTrue(fetchedProcessingByTicketId.isPresent());
    }
}