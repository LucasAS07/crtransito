package io.lrsystem.crtransito.domain.model;

import io.lrsystem.crtransito.domain.exception.NegocioException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //@JoinColumn(name = "proprietario_id")
    @ManyToOne
    private Proprietario proprietario;
    private String marca;
    private String modelo;
    private String placa;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "veiculo",cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao){
        autuacao.setDataAutuacao(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);

        return autuacao;
    }

    public void apreender(){
        if (estaApreendido()){
            throw new NegocioException("Veiculo já está apreendido");
        }
        setStatus(Status.APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }

    public void liberarApreencao(){
        if (!estaApreendido()){
            throw new NegocioException("Veiculo já está liberado");
        }

        setStatus(Status.REGULAR);
        setDataApreensao(null);
    }

    public boolean estaApreendido(){
        return Status.APREENDIDO.equals(getStatus());
    }

}
