package com.dev.kimura.SetupFinder.Controller;

import com.dev.kimura.SetupFinder.Model.Enum.SetupComponents;
import com.dev.kimura.SetupFinder.Model.SetupItemDTO;
import com.dev.kimura.SetupFinder.Model.SetupItemModel;
import com.dev.kimura.SetupFinder.Service.ChatGptService;
import com.dev.kimura.SetupFinder.Service.SetupItemService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class SetupController {


    private ChatGptService chatGptService;
    private SetupItemService setupItemService;

    public SetupController(ChatGptService chatGptService, SetupItemService setupItemService ) {
        this.chatGptService = chatGptService;
        this.setupItemService = setupItemService;
    }

    @PostMapping("/gerarPrompt")
    public Mono<String> gerarPrompt(Model model) {

        List<SetupItemDTO> setupItens = setupItemService.listarComponentes();

        model.addAttribute("componentes", setupItens);
        model.addAttribute("tiposComponentes", SetupComponents.values());

        if (setupItens == null || setupItens.isEmpty()) {
            model.addAttribute("promptGerado", "Nenhum componente cadastrado.");
            model.addAttribute("respostaIa", "Cadastre ao menos um componente para gerar uma sugestão de setup.");

            return Mono.just("dashboard");
        }

        String promptGerado = chatGptService.montarPromptVisual(setupItens);

        return chatGptService.generateSetup(setupItens)
                .map(respostaIa -> {
                    model.addAttribute("componentes", setupItens);
                    model.addAttribute("tiposComponentes", SetupComponents.values());
                    model.addAttribute("promptGerado", promptGerado);
                    model.addAttribute("respostaIa", respostaIa);

                    return "dashboard";
                });
    }
}
