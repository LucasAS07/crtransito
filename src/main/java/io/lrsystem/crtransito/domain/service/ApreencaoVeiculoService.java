package io.lrsystem.crtransito.domain.service;

import io.lrsystem.crtransito.domain.model.Status;
import io.lrsystem.crtransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ApreencaoVeiculoService {

    private final RegistroVeiculoService registroVeiculoService;

    @Transactional
    public void apreender(Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.apreender();
    }

    @Transactional
    public void liberarApreencao(Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.liberarApreencao();
    }

}
