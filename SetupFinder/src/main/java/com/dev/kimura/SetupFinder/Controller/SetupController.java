package com.dev.kimura.SetupFinder.Controller;

import com.dev.kimura.SetupFinder.Service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SetupController {

    private ChatGptService chatGptService;

    public SetupController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    public SetupController() {
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateSetup(){

        return chatGptService.generateSetup();
    }
}
