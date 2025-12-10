package net.asiedlecki.owocny.owocny_springai_demo.vector.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.asiedlecki.owocny.owocny_springai_demo.scraper.LinkFetcher;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DocumentsIngestionService implements CommandLineRunner {

    private final VectorStore vectorStore;

    @Value("classpath*:docs/**/*.*")
    private Resource[] docs;

    @Value("#{'${webpages.for.subpage.scan}'.split(',')}")
    private List<String> webPagesForSubpageScan;

    @Value("#{'${webpages.for.scan}'.split(',')}")
    private List<String> webPages;


    @Override
    public void run(String... args) {
        log.info("Docs reading");
        for (Resource doc: docs) {
            addResurceToVectorStore(doc);
            log.info("Wczytano doc z filesystemu do vectorStore: {}", doc.getFilename());
        }

        //loadSubPagesIntoVectorStore();

        webPages.forEach(webPage -> {
            try {
                addResurceToVectorStore(new UrlResource(webPage));
            } catch (MalformedURLException e) {
                log.error("Blad podczas ladowania strony: {}", e);
            }
            log.info("Wczytano strone do vectorStore: {}", webPage);

        });
    }

    private void loadSubPagesIntoVectorStore() {
        webPagesForSubpageScan.forEach(webPage -> {
            try {
                List<Resource> resources = LinkFetcher.fetchSubpages(webPage);
                resources.forEach(subpage -> {
                    addResurceToVectorStore(subpage);
                    log.info("Wczytano strone do vectorStore: {}", subpage);

                });
            } catch (IOException e) {
                log.error("Blad podczas pobierania: {}", webPage);
            }
        });
    }

    private void addResurceToVectorStore(Resource doc) {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(doc);
        try {
            List<Document> documents = tikaDocumentReader.get();
            vectorStore.add(documents);
        } catch (RuntimeException e) {
            log.error("Blad podczas odczytu dokumentu TikaDocumentReader lub dodawania do vectorStore", e);
        }
    }
}
