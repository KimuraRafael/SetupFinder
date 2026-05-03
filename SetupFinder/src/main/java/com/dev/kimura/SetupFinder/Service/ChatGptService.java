    package com.dev.kimura.SetupFinder.Service;

    import com.dev.kimura.SetupFinder.Model.SetupItemDTO;
    import com.dev.kimura.SetupFinder.Model.SetupItemModel;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.MediaType;
    import org.springframework.stereotype.Service;
    import org.springframework.web.reactive.function.client.WebClient;
    import reactor.core.publisher.Mono;

    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collectors;


@Service
public class ChatGptService {

    private final WebClient webClient;

    private String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    private String montarComponentes(List<SetupItemDTO> setupItens) {
        return setupItens.stream()
                .map(setup -> String.format(
                        "- %s | Componente: %s | Quantidade: %d",
                        setup.getDescricao(),
                        setup.getComponente(),
                        setup.getQuantidade()
                ))
                .collect(Collectors.joining("\n"));
    }

    public String montarPromptVisual(List<SetupItemDTO> setupItens) {

        String componentes = montarComponentes(setupItens);

        return """
                Baseado nos componentes cadastrados abaixo, monte uma sugestão de setup custo-benefício.

                Componentes cadastrados:
                %s

                Regras para resposta:
                - Analise se os componentes são compatíveis entre si.
                - Informe possíveis gargalos.
                - Sugira melhorias, se necessário.
                - Considere um bom equilíbrio entre custo e desempenho.
                - Caso algum componente essencial esteja faltando, informe claramente.
                """.formatted(componentes);
    }

    public Mono<String> generateSetup(List<SetupItemDTO> setupItens) {

        String prompt = montarPromptVisual(setupItens);

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o",
                "messages", List.of(
                        Map.of(
                                "role", "system",
                                "content", "Você é um assistente especializado em montagem de computadores e compatibilidade de hardware."
                        ),
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");

                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message =
                                (Map<String, Object>) choices.get(0).get("message");

                        return message.get("content").toString();
                    }

                    return "Forneça mais peças para um melhor resultado.";
                });
    }
}