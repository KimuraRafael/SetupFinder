package com.dev.kimura.SetupFinder.Controller;

import com.dev.kimura.SetupFinder.Model.SetupItemDTO;
import com.dev.kimura.SetupFinder.Service.SetupItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dev.kimura.SetupFinder.Model.Enum.SetupComponents;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class SetupControllerUI {

    private final SetupItemService setupItemService;

    SetupControllerUI (SetupItemService setupItemService){

        this.setupItemService = setupItemService;

    }

    @GetMapping("/listarComponentes")
    public String listarComponentes(Model model) {

        carregarDadosDashboard(model);

        if (!model.containsAttribute("componentesSelecionados")) {
            model.addAttribute("componentesSelecionados", new ArrayList<>());
        }

        if (!model.containsAttribute("promptGerado")) {
            model.addAttribute("promptGerado", null);
        }

        if (!model.containsAttribute("respostaIa")) {
            model.addAttribute("respostaIa", null);
        }

        return "dashboard";
    }
    private void carregarDadosDashboard(Model model) {
        List<SetupItemDTO> componentes = setupItemService.listarComponentes();

        model.addAttribute("componentes", componentes);
        model.addAttribute("tiposComponentes", SetupComponents.values());
    }

}
