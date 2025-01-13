package io.lrsystem.crtransito.domain.service;

import io.lrsystem.crtransito.domain.exception.EntidadeNaoEncontradaException;
import io.lrsystem.crtransito.domain.exception.NegocioException;
import io.lrsystem.crtransito.domain.model.Proprietario;
import io.lrsystem.crtransito.domain.model.Status;
import io.lrsystem.crtransito.domain.model.Veiculo;
import io.lrsystem.crtransito.domain.repository.ProprietarioRepository;
import io.lrsystem.crtransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    public Veiculo buscar(Long veiculoId){
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(()->new EntidadeNaoEncontradaException("Veiculo não encontrado"));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo){
        if (novoVeiculo.getId() != null){
            throw new NegocioException("Veiculo para cadastro não pode ter um ID");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(novoVeiculo))
                                .isPresent();

        if (placaEmUso){
            throw new NegocioException("Já existe um veiculo cadastrado com esta placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(Status.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }

}
