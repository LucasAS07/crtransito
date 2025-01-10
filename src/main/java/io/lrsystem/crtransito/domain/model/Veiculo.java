package io.lrsystem.crtransito.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    //@JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;
    private String marca;
    private String modelo;
    private String placa;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataApreensao;

    @Enumerated(EnumType.STRING)
    private Status status;

}
