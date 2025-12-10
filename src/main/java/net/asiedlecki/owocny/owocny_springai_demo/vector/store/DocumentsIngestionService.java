package net.asiedlecki.owocny.owocny_springai_demo.vector.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DocumentsIngestionService implements CommandLineRunner {

    private final VectorStore vectorStore;

    @Value("classpath*:docs/**/*.*")
    private Resource[] docs;

    @Override
    public void run(String... args) {
        log.info("Docs reading");
        for (Resource doc: docs) {
            TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(doc);
            List<Document> documents = tikaDocumentReader.get();
            vectorStore.add(documents);

            log.info("Wczytano do vectorStore: {}", doc.getFilename());
        }
    }
}
