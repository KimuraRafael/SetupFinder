package com.dev.kimura.SetupFinder.Service;

import com.dev.kimura.SetupFinder.Model.SetupItemDTO;
import com.dev.kimura.SetupFinder.Model.SetupItemMapper;
import com.dev.kimura.SetupFinder.Model.SetupItemModel;
import com.dev.kimura.SetupFinder.Repository.SetupItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetupItemService {

    private SetupItemRepository setupRepository;
    private SetupItemMapper setupItemMapper;

    public SetupItemService(SetupItemRepository setupRepository, SetupItemMapper setupItemMapper) {
        this.setupRepository = setupRepository;
        this.setupItemMapper = setupItemMapper;
    }


    //Gravando o item adicionado
    public SetupItemModel salvarItemAdicionado(SetupItemModel setupItem) {

        return setupRepository.save(setupItem);

    }

    //Listando todos os componentes
    public List<SetupItemModel> listarComponentes() {
        return setupRepository.findAll();

    }

    //Listando todos os componentes
    public SetupItemDTO listarComponentesPorId(Long id) {

        Optional<SetupItemModel> componenteId = setupRepository.findById(id);
        return componenteId.map(setupItemMapper::map).orElse(null);
    }

    public SetupItemDTO atualizaComponente(Long id, SetupItemDTO setupAlterado) {

        return setupRepository.findById(id).map(setupExistente -> {

            if (setupAlterado.getDescricao() != null) {
                setupExistente.setDescricao(setupAlterado.getDescricao());
            }
            if (setupAlterado.getComponente() != null) {
                setupExistente.setComponente(setupAlterado.getComponente());
            }
            if (setupAlterado.getQuantidade() > 0) {
                setupExistente.setQuantidade(setupAlterado.getQuantidade());
            }
            if (setupAlterado.getValor() != null) {
                setupExistente.setValor(setupAlterado.getValor());
            }
            SetupItemModel componenteSalvo = setupRepository.save(setupExistente);
            return setupItemMapper.map(componenteSalvo);
        }).orElse(null);
    }

    //Deletar o componente
    public void deletarComponente(Long id){
        setupRepository.deleteById(id);
    }




}
