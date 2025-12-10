package net.asiedlecki.owocny.owocny_springai_demo.model.tools.input;

import io.swagger.v3.oas.annotations.media.Schema;

public record TaxInput(@Schema(description = "powierchnia_w_hektarach") double powierzchnia,
                       @Schema(description = "klasa_gleby") String klasaGleby) {
}
