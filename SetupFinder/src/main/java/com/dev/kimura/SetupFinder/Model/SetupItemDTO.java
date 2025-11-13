package com.dev.kimura.SetupFinder.Model;

import com.dev.kimura.SetupFinder.Model.Enum.SetupComponents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetupItemDTO {

    private Long id;
    private String descricao;
    private SetupComponents componente;
    private Double valor;
    private Integer quantidade;
    private LocalDate dataValor;

}
