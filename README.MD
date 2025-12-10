## Notatki

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

### Zrobiłem mechanizm dodawania stron internetowych do vectorStore.
Próbowałem dać link do aktualności, zaciągnąć html wszystkich href stamtąd i dodać do truststore.
Niestety odpowiedzi były słabe.
Dając pojedyńczą stronę, wynik jest bardzo dobry.
```agsl
Wczytano strone do vectorStore: https://www.gov.pl/web/arimr/platnosci-koncowe-trafiaja-do-rolnikow

http://localhost:8080/unstructured?message=Kiedy płatności końcowe z ARMIR trafią do rolników?


Płatności końcowe z ARMIR trafiają do rolników wraz z początkiem grudnia 2025 roku i potrwają do 30 czerwca 2026 roku. Do 9 grudnia 2025 r. ubiegający się o dopłaty otrzymali już 11,64 mld zł.
```

```agsl
http://localhost:8080/unstructured?message=Jakie sołectwa są w gminie Jasieniec?

Nie ma w podanym kontekście informacji o sołectwach w gminie Jasieniec. Aby uzyskać te dane, zalecałbym skorzystanie z serwisu互联网工具无法回答关于雅申采克市的村庄信息，因为提供的内容中没有这方面的信息。建议访问官方网站或使用地图服务获取此类详细的地方行政区划数据。请问您需要其他帮助吗？

 Po dodaniu https://pl.wikipedia.org/wiki/Jasieniec_(gmina) nadal halucynuje, możliwe że wciąga za dużo zbędnych informacji.

```

### Błędy ogólne

halucynacje niezwiązane z pytaniem, ale związane z tym co ma w vectorStore
```agsl
http://localhost:8080/unstructured?message=Jakie sołectwa są w gminie Jasieniec?

Czy masz na myśli klasy botaniczne gleb pod względem ujemnych cech? W Polsce klasy gleb zgodnie ze standardami geologicznymi są oznaczane jako I (najlepsza) do V (najgorsza), ale nie ma precyzyjnej informacji w Twoim pytaniu, która była by związana z konkretną listą klas gleb: I, II, III, IVa i IVb. Czy możesz dostarczyć więcej kontekstu na ten temat? Może to pomóc mi udzielić dokładniejszej odpowiedzi.
```

