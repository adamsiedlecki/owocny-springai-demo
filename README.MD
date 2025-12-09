Notatki

Mechanizm .entity do structured output jest chyba zawodny
```
2025-12-09T22:56:23.224+01:00 ERROR 9848 --- [owocny-springai-demo] [nio-8080-exec-4] o.s.ai.converter.BeanOutputConverter     : Could not parse the given text to the desired target type: "<|join_string_id|>
[{ "introduction" : "Oto rodzaje gleb występujących w Polsce:", "items" : [ "Czarnoziemy", "Rędziny", "Gleby brunatne", "Mady", "Gleby bagienne", "Gleby bielicowe", "Gleby torfowe", "Gleby piaszczyste", "Gleby gliniaste", "Gleby lessowe" ] }]" into class net.asiedlecki.owocny.owocny_springai_demo.model.StructuredListResponse
2025-12-09T22:56:23.225+01:00 ERROR 9848 --- [owocny-springai-demo] [nio-8080-exec-4] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: java.lang.RuntimeException: com.fasterxml.jackson.core.JsonParseException: Unexpected character ('<' (code 60)): expected a valid value (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 1]] with root cause

com.fasterxml.jackson.core.JsonParseException: Unexpected character ('<' (code 60)): expected a valid value (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 1]
```

Bielik v3 nie wspiera tools
``` 
HTTP 400 - {"error":"registry.ollama.ai/SpeakLeash/bielik-4.5b-v3.0-instruct:Q8_0 does not support tools"}
```
