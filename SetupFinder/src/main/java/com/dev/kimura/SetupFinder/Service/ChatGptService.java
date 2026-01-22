package com.dev.kimura.SetupFinder.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;

    private String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Requisição que prepara o prompt
    public Mono<String> generateSetup() {

        String prompt = "Me sugira o melhor custo x benefício com de acordo com as peças fornecidas";
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-5.2",
                "mensages", List.of(
                        Map.of("role", "system", "content", "Você deve buscar pelas peças compatíveis de acordo com as fornecidas"),
                        Map.of("role", "use", "content", prompt)
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

                    return "Forneça mais peças para um melhor resultado";


                });
    }
}
