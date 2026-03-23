package com.dev.kimura.SetupFinder.Controller;

import com.dev.kimura.SetupFinder.Model.SetupItemDTO;
import com.dev.kimura.SetupFinder.Model.SetupItemModel;
import com.dev.kimura.SetupFinder.Service.ChatGptService;
import com.dev.kimura.SetupFinder.Service.SetupItemService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class SetupController {


    private ChatGptService chatGptService;
    private SetupItemService setupItemService;

    public SetupController(ChatGptService chatGptService, SetupItemService setupItemService ) {
        this.chatGptService = chatGptService;
        this.setupItemService = setupItemService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateSetup(){

        List<SetupItemDTO> setupItens = setupItemService.listarComponentes();

        return chatGptService.generateSetup(setupItens)
                .map(setup -> ResponseEntity.ok(setup))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }


}
