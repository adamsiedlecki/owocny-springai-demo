package net.asiedlecki.owocny.owocny_springai_demo.tools;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class TaxTools {

    @Tool(description = "Oblicza stawkę podatku rolnego")
    public double calculateTax(
            @Schema(description = "powierchniaHa") double area,
            @Schema(description = "klasa ziemi - może być I, II, III, IVa, IVb") String klasaZiemi,
            @Schema(description = "strefa - może być miejska, podmiejska, wiejska, wiejska oddalona") String strefa
    ) {
        if (klasaZiemi.equals("I")) {
            return area * 1000;
        }
        return switch (strefa) {
            case "miejska" -> area * 100;
            case "podmiejska" -> area * 90;
            case "wiejska" -> area * 80;
            case "wiejska oddalona" -> area * 50;
            default -> 2137;
        };
    }
}
