package com.dev.kimura.SetupFinder.Controller;

import com.dev.kimura.SetupFinder.Model.SetupItemDTO;
import com.dev.kimura.SetupFinder.Service.SetupItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class SetupControllerUI {

    private final SetupItemService setupItemService;

    SetupControllerUI (SetupItemService setupItemService){

        this.setupItemService = setupItemService;

    }

/*    @GetMapping("/listarComponentes")
    public String listarComponentes(Model model){

        List<SetupItemDTO> listarComponentes = setupItemService.listarComponentes();

    }*/



}
