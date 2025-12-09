package net.asiedlecki.owocny.owocny_springai_demo.tools;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class TaxTools {

    @Tool(description = "Oblicza stawkę podatku rolnego")
    public double calculateTax(
            @Schema(description = "powierchniaHa") double area,
            @Schema(description = "klasa ziemi - może być I, II, III, IVa, IVb") String klasaZiemi
    ) {
        System.out.println("TaxTools invoked");
        return switch (klasaZiemi) {
            case "I" -> area * 100;
            case "II" -> area * 90;
            case "III" -> area * 80;
            case "IVa" -> area * 50;
            case "IVb" -> area * 30;
            default -> 2137;
        };
    }
}
