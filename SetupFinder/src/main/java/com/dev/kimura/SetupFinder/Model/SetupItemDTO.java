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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SetupComponents getComponente() {
        return componente;
    }

    public void setComponente(SetupComponents componente) {
        this.componente = componente;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataValor() {
        return dataValor;
    }

    public void setDataValor(LocalDate dataValor) {
        this.dataValor = dataValor;
    }
}
