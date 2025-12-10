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

Bielik v3, deepseek r1, deepseek v2 nie wspiera tools
``` 
HTTP 400 - {"error":"registry.ollama.ai/SpeakLeash/bielik-4.5b-v3.0-instruct:Q8_0 does not support tools"}
HTTP 400 - {"error":"registry.ollama.ai/library/deepseek-r1:14b does not support tools"}
HTTP 400 - {"error":"registry.ollama.ai/library/deepseek-v2:16b does not support tools
```

Błąd - dane wejściowe do toola muszą być obiektem albo lepiej - należy używać @ToolParam
```agsl
java.lang.IllegalStateException: No ToolCallback found for tool name: narzedie_do_obliczania_podatku_rolnego
	at org.springframework.ai.model.tool.DefaultToolCallingManager.executeToolCall(DefaultToolCallingManager.java:219) ~[spring-ai-model-1.1.2.jar:1.1.2]
	at org.springframework.ai.model.tool.DefaultToolCallingManager.executeToolCalls(DefaultToolCallingManager.java:143) ~[spring-ai-model-1.1.2.jar:1.1.2]
```

Potrzebne do odczytywania dokumentów do vectorStore
```
ollama pull mxbai-embed-large
```

Udało się wczytać html do vectorStore
```agsl
http://localhost:8080/unstructured?message=Do czego służy owocny.com

Owocny.com jest serwisem oferującym system zarządzania sadem (OMS) dla owocowych gospodarstw. Oferuje on narzędzia takie jak:

- Łatwe rozliczenia akordowe.
- Szybkie drukowanie etykiet z danymi o odmianach, daty zbiorów, klasa jakości itp., co ułatwia magazynowanie towaru oraz spełnia wymagania formalne związane z znakowaniem owoców.
- Analiza wydajności upraw per kwarta/odmiana/rok posadzenia/wysokość kwatery n.p.m. (dane pobierane są z geoportal.gov.pl).
- Możliwość śledzenia zmian w uprawach i tworzenie nowych map upraw rocznie.
- Raport akordowy wysyłany na maila, co ułatwia rozliczanie się z kontrahentami bez potrzeby podliczania ręcznie zeszytu.

Sytsem jest zaprojektowany tak, aby upraszczać proces zarządzania owocowymi gospodarstwami i ułatwiać pracę zarówno na polach, jak i przy analizie danych.
```

