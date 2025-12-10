package net.asiedlecki.owocny.owocny_springai_demo.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LinkFetcher {

    public static List<Resource> fetchSubpages(String webPage) throws IOException {
        List<Resource> resources = new ArrayList<>();

        Document doc = Jsoup.connect(webPage).get();

        Elements links = doc.select("a[href]");

        for (Element link : links) {
            String href = link.attr("abs:href");

            if (!href.isEmpty()) {
                try {
                    Resource resource = new UrlResource(href);
                    resources.add(resource);
                } catch (MalformedURLException e) {
                    // pomiń błędne URL
                }
            }
        }

        return resources;
    }

    public static Resource fetchPage(String webPage) throws IOException {
        Document doc = Jsoup.connect(webPage).get();
        String html = doc.html();

        byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
        return new ByteArrayResource(bytes) {
            @Override
            public String getFilename() {
                return "page.html";
            }
        };
    }
}
