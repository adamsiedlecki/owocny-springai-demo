package net.asiedlecki.owocny.owocny_springai_demo.model;

import java.util.List;

public record StructuredListResponse(String introduction, List<String> items) {
}
