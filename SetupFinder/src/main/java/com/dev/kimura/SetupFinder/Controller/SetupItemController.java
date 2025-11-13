package com.dev.kimura.SetupFinder.Controller;

import com.dev.kimura.SetupFinder.Model.SetupItemDTO;
import com.dev.kimura.SetupFinder.Model.SetupItemModel;
import com.dev.kimura.SetupFinder.Service.SetupItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setup")
public class SetupItemController {

    private SetupItemService setupItemService;

    public SetupItemController(SetupItemService setupItemService) {
        this.setupItemService = setupItemService;
    }



    //POST
    @PostMapping("/adicionarComponente")
    private ResponseEntity adicionarComponente(@RequestBody SetupItemModel setupItem){

        SetupItemModel adicionado = setupItemService.salvarItemAdicionado(setupItem);
        return ResponseEntity.ok(adicionado);
    }

    //GET
    @GetMapping("/listarComponentes")
    private ResponseEntity exibirComponentesAdicionados(){

        List<SetupItemModel> listaComponentes = setupItemService.listarComponentes();
        return ResponseEntity.ok(listaComponentes);
    }

    //UPDATE
    @PutMapping("/atualizarComponente/{id}")
    private ResponseEntity<String> atualizarComponente(@PathVariable Long id, @RequestBody SetupItemDTO componenteAtualizado){

        if(setupItemService.listarComponentesPorId(id) != null){
            SetupItemDTO componenteAnterior = setupItemService.listarComponentesPorId(id);
            SetupItemDTO componenteAtual = setupItemService.atualizaComponente(id, componenteAtualizado);
            return ResponseEntity.ok().body("Componente Alterado com Sucesso!\n");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Componente Não encontrado para edição");
    }


    //DELETE
    @DeleteMapping("/deletarComponente/{id}")
    private ResponseEntity<String> deletarComponente(@PathVariable Long id){

        if(setupItemService.listarComponentesPorId(id) != null) {
            setupItemService.deletarComponente(id);
            return ResponseEntity.ok().body("Componente Deletado com sucesso");
        }
        return ResponseEntity.ok().body("Componente não encontrado");
    }
}
