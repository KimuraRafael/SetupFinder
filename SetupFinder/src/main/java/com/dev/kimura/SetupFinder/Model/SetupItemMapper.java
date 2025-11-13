package com.dev.kimura.SetupFinder.Model;

import org.springframework.stereotype.Component;

@Component
public class SetupItemMapper {

    public SetupItemModel map(SetupItemDTO setupDTO){
        SetupItemModel setupMapeado = new SetupItemModel();
        setupMapeado.setDescricao(setupDTO.getDescricao());
        setupMapeado.setComponente(setupDTO.getComponente());
        setupMapeado.setValor(setupDTO.getValor());
        setupMapeado.setDataValor(setupDTO.getDataValor());
        setupMapeado.setQuantidade(setupDTO.getQuantidade());
        return setupMapeado;
    }

    public SetupItemDTO map(SetupItemModel setupModel){
        SetupItemDTO setupDTO = new SetupItemDTO();
        setupDTO.setDescricao(setupModel.getDescricao());
        setupDTO.setComponente(setupModel.getComponente());
        setupDTO.setValor(setupModel.getValor());
        setupDTO.setDataValor(setupModel.getDataValor());
        setupDTO.setQuantidade(setupModel.getQuantidade());
        return setupDTO;
    }


}
