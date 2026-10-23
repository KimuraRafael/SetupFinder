package com.dev.kimura.SetupFinder.Service;

import com.dev.kimura.SetupFinder.Model.Enum.SetupComponents;
import com.dev.kimura.SetupFinder.Model.SetupItemDTO;
import com.dev.kimura.SetupFinder.Model.SetupItemMapper;
import com.dev.kimura.SetupFinder.Model.SetupItemModel;
import com.dev.kimura.SetupFinder.Repository.SetupItemRepository;
import org.checkerframework.checker.optional.qual.OptionalCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SetupItemServiceTest {

    @Mock
    private SetupItemRepository repository;

    @Spy
    private SetupItemMapper mapper;

    @InjectMocks
    private SetupItemService service;

    @Test
    void deveRetornarNullQuandoComponenteNaoExiste() {
        // Arrange: o repository "finge" que não encontrou nada
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // Act
        SetupItemDTO resultado = service.listarComponentesPorId(999L);

        // Assert
        assertThat(resultado).isNull() ;
    }

    @Test
    void deveRetornarComponenteQuandoIdExiste() {
        // Arrange: monta um componente "do banco"
        SetupItemModel componente = new SetupItemModel();
        componente.setId(1L);
        componente.setDescricao("Ryzen 5600G");
        componente.setComponente(SetupComponents.Processadores);
        componente.setQuantidade(2);

        when(repository.findById(1L)).thenReturn(Optional.of(componente));

        // Act
        SetupItemDTO resultado = service.listarComponentesPorId(1L);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getDescricao()).isEqualTo("Ryzen 5600G");
        assertThat(resultado.getQuantidade()).isEqualTo(2);
    }
}