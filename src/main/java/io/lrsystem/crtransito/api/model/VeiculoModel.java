package io.lrsystem.crtransito.api.model;

import io.lrsystem.crtransito.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoModel {

    private Long id;
    private ProprietarioResumoModel proprietario;
    private String marca;
    private String modelo;
    private String placaVeiculo;
    private Status status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

}
