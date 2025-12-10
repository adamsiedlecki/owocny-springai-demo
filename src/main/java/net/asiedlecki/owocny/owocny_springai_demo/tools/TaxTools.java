package net.asiedlecki.owocny.owocny_springai_demo.tools;

import lombok.extern.slf4j.Slf4j;
import net.asiedlecki.owocny.owocny_springai_demo.model.tools.tax.TaxOutput;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TaxTools {

    @Tool(name="obliczanie_podatku_rolnego")
    public double calculateTax(@ToolParam(description = "powierchnia_w_hektarach") double powierzchnia,
                               @ToolParam(description = "nazwa_klasy_gleby") String klasaGleby) {
        log.info("TaxTools invoked - calculateTax {} Ha, klasa: {}", powierzchnia, klasaGleby);
        return switch (klasaGleby) {
            case "I" -> powierzchnia * 100;
            case "II" -> powierzchnia * 90;
            case "III" -> powierzchnia * 80;
            case "IVa" -> powierzchnia * 50;
            case "IVb" -> powierzchnia * 30;
            default -> 2137;
        };
    }

    @Tool(name="pobieranie_nazw_klas_gleb_w_polsce")
    public TaxOutput getAreaTypes() {
        log.info("TaxTools invoked - getAreaTypes");
        return new TaxOutput(List.of("I", "II", "III", "IVa",  "IVb"));
    }
}
