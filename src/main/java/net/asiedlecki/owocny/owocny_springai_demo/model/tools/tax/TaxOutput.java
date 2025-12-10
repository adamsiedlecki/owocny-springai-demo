package net.asiedlecki.owocny.owocny_springai_demo.model.tools.tax;

import org.springframework.ai.tool.annotation.ToolParam;

import java.util.List;

public record TaxOutput(@ToolParam(description = "lista klas gleb")List<String> klasyGleb) {
}
