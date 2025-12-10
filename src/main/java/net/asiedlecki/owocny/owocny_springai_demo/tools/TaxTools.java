package net.asiedlecki.owocny.owocny_springai_demo.tools;

import net.asiedlecki.owocny.owocny_springai_demo.model.tools.input.TaxInput;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxTools {

    @Tool(name="obliczanie_podatku_rolnego", description = "Oblicza_podatek_rolny")
    public double calculateTax(TaxInput input) {
        System.out.println("TaxTools invoked - calculateTax");
        double powierzchnia = input.powierzchnia();
        return switch (input.klasaGleby()) {
            case "I" -> powierzchnia * 100;
            case "II" -> powierzchnia * 90;
            case "III" -> powierzchnia * 80;
            case "IVa" -> powierzchnia * 50;
            case "IVb" -> powierzchnia * 30;
            default -> 2137;
        };
    }

    @Tool(name="pobieranie_klas_gleby", description = "Pobiera_rodzaje_klas_gleby")
    public List<String> getAreaTypes() {
        System.out.println("TaxTools invoked - getAreaTypes");
        return List.of("I", "II", "III", "IVa",  "IVb");
    }
}
