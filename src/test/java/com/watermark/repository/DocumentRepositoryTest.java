package com.watermark.repository;

import com.watermark.model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    @BeforeEach
    void setUp() {
        documentRepository.deleteAllInBatch();
    }

    @Test
    void findAllByTicketId() {
        final Document mockDocument =
                new Document("mockTicketId", "mockContent", "mockTitle", "mockAuthor", "mockTopic");
        documentRepository.save(mockDocument);

        final Optional<Document> mockTicketId =
                documentRepository.findAllByTicketId("mockTicketId");

        assertTrue(mockTicketId.isPresent());
    }
}