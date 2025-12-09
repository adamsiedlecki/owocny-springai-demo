package net.asiedlecki.owocny.owocny_springai_demo.model;

import java.util.Map;

public record StructuredListResponse(String wstep, Map<String, String> rodzajeOrazStawki) {
}
